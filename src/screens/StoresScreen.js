import React from 'react'
import {Text, View, StyleSheet} from 'react-native'
import {Button} from 'react-native-elements'

const StoresScreen = ({navigation}) => {
    return(
        <View>
            <Text style={styles.title}>StoresScreen</Text>
            <Button
                onPress={() => navigation.navigate('StoreDetail')}
                title="Go to Store Detail Screen"
            />
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default StoresScreen;