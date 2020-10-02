import React, {useContext, useEffect, useState} from 'react'
import {Text, View, StyleSheet, ScrollView, Dimensions} from 'react-native'
import { TouchableOpacity } from 'react-native-gesture-handler';
import { exp } from 'react-native-reanimated';
import { SafeAreaView } from 'react-native-safe-area-context';

import BackIconSvg from '../../assets/img/BackIconSvg'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height;

const ScreenHeader = ({title, onPress}) => {
    return (
            <View style={{flexDirection:'row', position: 'absolute', flexGrow: 1, justifyContent: 'space-between', width: '100%'}}>
                <TouchableOpacity style={{justifyContent: 'center'}} onPress={onPress}>
                    <BackIconSvg/>
                </TouchableOpacity>
            <View style={{justifyContent: 'center', paddingRight: width*0.2, paddingBottom: height*0.005}}>
                    {title ? <Text style={styles.title}>{title}</Text> : null}
                </View>
                <View></View>
            </View>
    );
}
const styles = StyleSheet.create({
    title: {
        fontSize: 18,
        color: '#FFFFFF',
        fontWeight: 'bold'
    }
});

export default ScreenHeader;