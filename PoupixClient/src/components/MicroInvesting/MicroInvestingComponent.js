import React, { useState } from 'react'
import {Text, View, StyleSheet, Dimensions, Button} from 'react-native'
import formatNumber from '../../functions/formatNumber'
import Slider from '@react-native-community/slider';
import CheckBox from '@react-native-community/checkbox';
import poupixApi from '../../api/poupixApi'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const MicroInvestingComponent = ({roundup}) => {
    const [sliderValue, setSliderValue] = useState(roundup ? roundup.roundup: 0.0)
    const [roundupTransaction, setroundupTransaction] = useState(roundup ? false : true)

    const roundUpValueUpdate = (value, type) => {
        if(type == 'roundup'){
            setroundupTransaction(value)
            if(value == true){
                value = null
            }else{
                value = +sliderValue.replace(',','.')
            }
        }else if(type == 'slider'){
            setSliderValue(formatNumber(value))
            value = value.toFixed(2)
        }
        poupixApi.put('/accounts/roundup', {roundup: value})
    }

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
                    <Slider
                        style={{width: width * 0.7, height: height * 0.05}}
                        disabled={roundupTransaction ? true : false}
                        minimumValue={0}
                        maximumValue={7}
                        value={sliderValue}
                        minimumTrackTintColor="#8516FA"
                        maximumTrackTintColor="#707070"
                        thumbTintColor="#8516FA"
                        onSlidingComplete = {(value) => roundUpValueUpdate(value, 'slider')}
                        onValueChange = {(value) => setSliderValue(formatNumber(value))}
                    />
                    <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                        <Text style={styles.minimumValue}>R$ 0</Text>
                        <Text style={styles.maximumValue}>R$ 7</Text>
                    </View>
                    <View style={styles.sliderValueBox}>
                        <Text style={{textAlign: 'center', color: '#FFFFFF'}}>R${roundupTransaction ? '0,00': sliderValue}</Text>
                    </View>
                    <View style={styles.roundupCheckboxView}>
                        <CheckBox
                            disabled={false}
                            value={roundupTransaction}
                            onCheckColor='#8516FA'
                            tintColors={{ true : '#8516FA', false: '#8516FA'}}
                            lineWidth={1}
                            onValueChange={(newValue) => roundUpValueUpdate(newValue, 'roundup')}
                        />
                        <View style={{justifyContent: 'center', marginTop: height* 0.005}}>
                            <Text style={{fontStyle: 'italic', fontSize: 16}}>Arredondamento</Text>
                            <Text style={{fontSize: 10}}>Escolha essa opção para arredondar o valor{'\n'}das compras para o maior inteiro mais próximo.</Text>
                        </View>
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
    sliderValueBox: {
        backgroundColor: '#8516FA',
        overflow: 'hidden',
        borderRadius: 10,
        width: width * 0.15,
        height: height * 0.05,
        justifyContent: 'center',
        alignSelf: 'center',
        marginRight: width * 0.15
    },
    minimumValue:{
        marginLeft: width*0.03
    },
    maximumValue: {
        marginRight: width * 0.16
    },
    roundupCheckboxView: {
        flexDirection: 'row',
        marginTop: height * 0.02
    }
});

export default MicroInvestingComponent;