import React from 'react'
import {Text, View, StyleSheet} from 'react-native'

const MicroInvestingScreen = ({navigation}) => {
    return(
        <View>
            <Text style={styles.title}>MicroInvestingScreen</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default MicroInvestingScreen;