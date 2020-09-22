import React from 'react'
import {Text, View, StyleSheet} from 'react-native'
import {Button} from 'react-native-elements'

const MenuScreen = ({navigation}) => {
    return(
        <View>
            <Text style={styles.title}>MenuScreen</Text>
            <Button
                onPress={() => navigation.navigate('Goals')}
                title="Go to Goals"
            />
            <Button
                onPress={() => navigation.navigate('MicroInvesting')}
                title="Go to Micro Investing"
            />
            <Button
                onPress={() => navigation.navigate('Stores')}
                title="Go to Stores"
            />
            
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default MenuScreen;