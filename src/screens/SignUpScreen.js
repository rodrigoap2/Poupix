import React from 'react'
import {Text, View, StyleSheet} from 'react-native'

const SignUpScreen = () => {
    return(
        <View>
            <Text style={styles.title}>SignUpScreen</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default SignUpScreen;