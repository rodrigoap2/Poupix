import React from 'react'
import {Text, View, StyleSheet} from 'react-native'

const StoreDetailScreen = ({navigation}) => {
    return(
        <View>
            <Text style={styles.title}>StoreDetailScreen</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default StoreDetailScreen;