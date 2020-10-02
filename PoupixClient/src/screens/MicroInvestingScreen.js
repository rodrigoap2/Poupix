import React, {useEffect, useState} from 'react'
import {StyleSheet, View} from 'react-native'
import GoalsView from '../components/Goals/GoalsView';
import MicroInvestingComponent from '../components/MicroInvesting/MicroInvestingComponent';
import getGoals from '../functions/getGoals'

const MicroInvestingScreen = ({navigation}) => {
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
            <GoalsView navigation={navigation} title={'Micro-investimento'} name={name} goals={goals} component={<MicroInvestingComponent roundup={goals.roundup}/>}/>
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

export default MicroInvestingScreen;