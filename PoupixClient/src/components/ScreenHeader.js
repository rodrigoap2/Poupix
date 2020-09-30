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
            <View style={{flexDirection:'row', position: 'absolute'}}>
                <TouchableOpacity style={{justifyContent: 'center'}} onPress={onPress}>
                    <BackIconSvg/>
                </TouchableOpacity>
                <View style={{justifyContent: 'center', left: width * 0.22}}>
                    {title ? <Text style={styles.title}>{title}</Text> : null}
                </View>
            </View>
    );
}
const styles = StyleSheet.create({
    title: {
        fontSize: 24,
        alignSelf: 'center',
        color: '#FFFFFF'
    }
});

export default ScreenHeader;