import React from 'react'
import {Text, View, StyleSheet, Dimensions} from 'react-native'
import {Button} from 'react-native-elements'
import GoalsView from '../components/Goals/GoalsView';
import ScreenHeader from '../components/ScreenHeader'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const GoalsScreen = ({navigation}) => {
    const name = navigation.getParam('name')
    const goals = navigation.getParam('goals')
    return(
        <GoalsView navigation={navigation} title={'Metas'} name={name} goals={goals}/>
    )
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