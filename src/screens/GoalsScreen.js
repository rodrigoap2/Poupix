import React from 'react'
import {Text, View, StyleSheet} from 'react-native'
import {Button} from 'react-native-elements'

const GoalsScreen = ({navigation}) => {
    return(
        <View>
            <Text style={styles.title}>GoalsScreen</Text>
            <Button
                onPress={() => navigation.navigate('GoalDetail')}
                title="Go to Goal Detail"
            />
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    }
});

export default GoalsScreen;