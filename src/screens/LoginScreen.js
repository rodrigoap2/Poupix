import React from 'react'
import {Text, View, StyleSheet} from 'react-native'
import {Button} from 'react-native-elements'

const LoginScreen = ({navigation}) => {
    return(
        <View>
            <Text style={styles.title}>LoginScreen</Text>
            <Button
                onPress={() => navigation.navigate('Menu')}
                title="Go to menu"
            />
            <Button
                onPress={() => navigation.navigate('Signup')}
                title="Go to Signup"
            />
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default LoginScreen;