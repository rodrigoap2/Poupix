import React, { useState } from 'react'
import {Text, View, StyleSheet, Dimensions, Button} from 'react-native'
import formatNumber from '../../functions/formatNumber'
import Slider from '@react-native-community/slider';


const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const MicroInvestingComponent = ({roundup}) => {
    const [sliderValue, setSliderValue] = useState(roundup ? roundup.roundup : 0)
    const [roundupTransaction, setroundupTransaction] = useState(roundup ? false : true)

    console.log(roundup)
    return (
        <View style={styles.container}>
            <View style={styles.microInvestingInfo}>
                <Text style={styles.totalValue}>R${formatNumber(roundup.total)}</Text>
                <Text style={styles.totalValueDescription}>Economizados em micro-investimentos até agora</Text>
                <Text style={styles.totalValueDescription}>Último mês: <Text style={styles.lastMonth}>R$ {formatNumber(roundup.lastMonth)}</Text></Text>
            </View>
            <View style={styles.roundUpView}>
                <Text style={styles.roundupTitle}>Micro-investimento</Text>
                <View style={{marginTop: height * 0.015, marginLeft: width * 0.05}}>
                    <Text style={styles.microInvestingDescription}>Esse valor será acrescido às suas compras e{'\n'}adicionado ao seu saldo automaticamente.</Text>
                    <View></View>
                    <Slider
                        style={{width: width * 0.7, height: height * 0.05}}
                        minimumValue={0}
                        maximumValue={7}
                        value={sliderValue}
                        minimumTrackTintColor="#8516FA"
                        maximumTrackTintColor="#707070"
                        thumbTintColor="#8516FA"
                        onValueChange = {(value) => setSliderValue(formatNumber(value))}
                    />
                    <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                    <Text style={{marginLeft: width*0.03}}>R$ 0</Text>
                    <Text style={{marginRight: width * 0.16}}>R$ 7</Text>
                    </View>
                    <View style={styles.createGoalButton}>
                        <Text style={{textAlign: 'center', color: '#FFFFFF'}}>R${sliderValue}</Text>
                    </View>
                </View>
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
    },
    createGoalButton: {
        backgroundColor: '#8516FA',
        overflow: 'hidden',
        borderRadius: 10,
        width: width * 0.15,
        height: height * 0.05,
        justifyContent: 'center',
        alignSelf: 'center',
        marginRight: width * 0.15
    },
});

export default MicroInvestingComponent;