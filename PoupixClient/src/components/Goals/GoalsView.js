import React from 'react'
import {Text, View, StyleSheet, Dimensions} from 'react-native'
import ScreenHeader from '../ScreenHeader'
import BalanceView from '../BalanceView';
import GoalsComponent from './GoalsComponent'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const GoalsView = ({navigation, title, name, goals}) => {
    return(
        <View style={styles.container}>
            <View style={{ marginTop: height * 0.04}}> 
                <ScreenHeader title={title} onPress={() => navigation.navigate('Menu')}/>
                <Text style={styles.name}>Olá, {'\n'}<Text style={{fontWeight:'bold'}}>{name}</Text></Text>
                <BalanceView balance={goals.account.balance} balanceLastYear={goals.account.lastYear} revenue={goals.account.revenue} revenueIndex={goals.account.revenueIndex}/>
            </View>
            <View style={styles.goalsComponent}>
                <GoalsComponent goals={goals.goals}/>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    },
    container: {
        backgroundColor: '#FFB726',
        flex: 1
    },
    name: {
        fontSize: 24,
        color: '#FFFFFF',
        textAlign: 'center',
        marginTop: height * 0.1,
        marginBottom: height * 0.05
    },
    goalsComponent: {
        backgroundColor: '#FFFFFF',
        flex: 1
    }
});

export default GoalsView;