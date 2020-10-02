import React, {useState, useEffect} from 'react'
import {Text, View, StyleSheet, Dimensions} from 'react-native'
import {Button} from 'react-native-elements'
import GoalsView from '../components/Goals/GoalsView';
import ScreenHeader from '../components/ScreenHeader'
import GoalsComponent from '../components/Goals/GoalsComponent';
import getGoals from '../functions/getGoals'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const GoalsScreen = ({navigation}) => {
    const name = navigation.getParam('name') ? navigation.getParam('name') : 'Rodrigo'
    const [goals,setGoals] = useState({})
    const [carregou, setCarregou] = useState(false)

    useEffect(() => {
        if(navigation.getParam('goals')){
            setGoals(navigation.getParam('goals'))
        }else{
            setGoals(getGoals())
        }
        setCarregou(true)
    },[])

    if(carregou){
        return(
            <GoalsView navigation={navigation} title={'Metas'} name={name} goals={goals} component={<GoalsComponent goals={goals.goals}/>}/>
        )
    }else{
        return(
            <View>

            </View>
        )
    }
    
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    },
    container: {
        backgroundColor: '#FFB726',
        flex: 1
    }
});

export default GoalsScreen;