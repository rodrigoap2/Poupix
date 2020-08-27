import React from 'react'
import {Text, View, StyleSheet} from 'react-native'

const GoalDetailScreen = ({navigation}) => {
    return(
        <View>
            <Text style={styles.title}>GoalDetailScreen</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default GoalDetailScreen;