import React from 'react'
import {Text, View, StyleSheet, Dimensions, Button} from 'react-native'
import { FlatList, TouchableOpacity } from 'react-native-gesture-handler';
import { round } from 'react-native-reanimated';
import formatNumber from '../../functions/formatNumber'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const MicroInvestingComponent = ({roundup}) => {

    return (
        <View style={styles.container}>
            <View style={styles.microInvestingInfo}>
                <Text style={styles.totalValue}>R${roundup.total}</Text>
                <Text style={styles.totalValueDescription}>Economizados em micro-investimentos até agora</Text>
                <Text style={styles.totalValueDescription}>Último mês: <Text style={styles.lastMonth}>R$ {formatNumber(roundup.lastMonth)}</Text></Text>
            </View>
            <View style={styles.roundUpView}>
                <Text style={styles.roundupTitle}>Micro-investimento</Text>
                <Text style={styles.microInvestingDescription}>Esse valor será acrescido às suas compras e{'\n'}adicionado ao seu saldo automaticamente.</Text>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1
    },
    microInvestingInfo: {
        marginTop: height * 0.02
    },
    totalValue: {
        textAlign: 'center',
        color: '#64B330',
        fontWeight: 'bold',
        fontSize: height*0.024
    },
    totalValueDescription: {
        textAlign: 'center',
        fontSize: height * 0.0135    
    },
    lastMonth: {
        color: '#A18DB5'
    },
    roundupTitle: {
        fontSize: height*0.024,
        fontWeight: 'bold',
    },
    roundUpView: {
        marginLeft: width * 0.1,
        marginTop: height * 0.02
    },
    microInvestingDescription: {
        fontSize: height * 0.0135,
        textAlign: 'left',
        marginTop: height * 0.015,
        marginLeft: width * 0.05
    },
});

export default MicroInvestingComponent;