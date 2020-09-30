import React from 'react'
import {Text, View, StyleSheet, Dimensions} from 'react-native'
import {Button} from 'react-native-elements'
import ScreenHeader from '../components/ScreenHeader'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const GoalsScreen = ({navigation}) => {
    return(
        <View style={{ marginTop: height * 0.04}}> 
            <ScreenHeader title={'Metas'} onPress={() => navigation.navigate('Menu')}/>
        </View>
    )
}

GoalsScreen.navigationOptions = () => {
    return{
        header: () => false
    }
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default GoalsScreen;