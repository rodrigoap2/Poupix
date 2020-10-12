import React from 'react'
import {Text, View, StyleSheet, Dimensions, SafeAreaView} from 'react-native'
import ScreenHeader from '../ScreenHeader'
import BalanceView from '../BalanceView';
import Spacer from '../Spacer';
import { ScrollView } from 'react-native-gesture-handler';

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const GoalsView = ({navigation, title, name, goals, component}) => {
    if(goals.account){
        return(
            <View style={styles.container}>
                <SafeAreaView style={{backgroundColor: '#FFB726'}}>
                    <View style={{marginTop: height * 0.04}}>
                        <ScreenHeader title={title} onPress={() => navigation.navigate('Menu')}/>
                    </View>
                        <Text style={styles.name}>Olá, {'\n'}<Text style={{fontWeight:'bold'}}>{name}</Text></Text>
                </SafeAreaView>
                <Spacer/>
                <View style={{backgroundColor: '#FFFFFF'}}>
                    <BalanceView balance={goals.account.balance} balanceLastYear={goals.account.lastYear} revenue={goals.account.revenue} revenueIndex={goals.account.revenueIndex}/>
                </View>
                <View style={styles.goalsComponent}>
                    <ScrollView>
                    {component}
                    </ScrollView>
                </View>
            </View>
        )
    }else{
        return(
            <View>

            </View>
        )
    }
    
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#FFFFFF',
    },
    name: {
        fontSize: 24,
        color: '#FFFFFF',
        textAlign: 'center',
        marginTop: height * 0.01,
        marginBottom: height * 0.05
    },
    goalsComponent: {
        backgroundColor: '#FFFFFF',
        flex: 1,
    }
});

export default GoalsView;