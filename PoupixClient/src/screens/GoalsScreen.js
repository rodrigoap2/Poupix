import React, {useState, useEffect} from 'react'
import {View, StyleSheet, Dimensions} from 'react-native'
import GoalsView from '../components/Goals/GoalsView';
import GoalsComponent from '../components/Goals/GoalsComponent';
import poupixApi from '../api/poupixApi'

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
            poupixApi.get('/accounts/general-information').then((res) => setGoals(res.data)).catch((err) => console.log(err))
        }
        setCarregou(true)
    },[])

    if(carregou){
        return(
            <GoalsView navigation={navigation} title={'Metas'} name={name} goals={goals} component={goals.goals ? <GoalsComponent goals={goals.goals}/> : <View></View>}/>
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