import React, {useState, useEffect} from 'react'
import {View, StyleSheet, Dimensions} from 'react-native'
import ScreenHeader from '../components/ScreenHeader'
import poupixApi from '../api/poupixApi'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const CreateGoalScreen = () => {
    return (
        <View>
            <ScreenHeader title="Nova meta"/>
        </View>
    );
}

const styles = StyleSheet.create({

});

export default CreateGoalScreen;