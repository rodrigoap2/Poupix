import React from 'react'
import {Text, View, StyleSheet, Dimensions, Image} from 'react-native'
import MicroInvestingIconSvg2 from '../../assets/img/MicroInvestingIconSvg2'
import {Button} from 'react-native-elements'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height;

const formatNumber = (amount, decimalCount = 2, decimal = ",", thousands = ".") => {
    try {
      decimalCount = Math.abs(decimalCount);
      decimalCount = isNaN(decimalCount) ? 2 : decimalCount;
  
      const negativeSign = amount < 0 ? "-" : "";
  
      let i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();
      let j = (i.length > 3) ? i.length % 3 : 0;
  
      return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : "");
    } catch (e) {
      console.log(e)
    }
  };

const BalanceView = ({balance, balanceLastYear, revenueIndex, revenue}) => {
    return (
        <View style={styles.objectView}>
            <View style={styles.blockView}>
                <View style={{justifyContent: 'center'}}>
                    <MicroInvestingIconSvg2/>
                </View>
                <View style={{flex: 1}}>
                    <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                        <Text style={styles.balance}>Saldo</Text>
                        <Text style={styles.balanceText}>R$ {formatNumber(balance)}</Text>
                    </View>
                    <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                        <Text style={styles.lastYear}>Últimos 12 meses</Text>
                        <Text style={styles.lastYearText}>+R$ {formatNumber(balanceLastYear)}</Text>
                    </View>
                </View>
            </View>
            <View style={styles.blockView}>
                <View style={{justifyContent: 'center'}}>
                    <MicroInvestingIconSvg2/>
                </View>
                <View style={{flex: 1}}>
                    <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                        <Text style={styles.balance}>Rendimentos</Text>
                        <Text style={styles.balanceText}>R$ {formatNumber(revenue)}</Text>
                    </View>
                    <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                        <Text style={styles.lastYear}>Rendendo</Text>
                        <Text style={styles.lastYearText}>{revenueIndex}</Text>
                    </View>
                </View>
            </View>
            <View style={styles.lastBlockView}>
                <View style={{justifyContent: 'center'}}>
                    <MicroInvestingIconSvg2/>
                </View>
                <View style={{flex: 1}}>
                    <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                        <Text style={styles.balance}>Valor Agendado</Text>
                        <Text style={styles.scheduledValue}>+R$ {formatNumber(revenue)}</Text>
                    </View>
                </View>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    objectView: {
        alignSelf: 'center',
        backgroundColor: '#FFFFFF',
        borderRadius: 20,
        borderWidth: 1,
        borderColor: '#DCDCDC',
        width: width * 0.8,
        height: height * 0.28,
        elevation: 3,
        shadowOffset:{  width: 1,  height: 1 },
        shadowColor: '#AAA',
        shadowOpacity: 1.0,
    },
    blockView: {
        flexDirection: 'row',
        borderBottomWidth: 1,
        borderBottomColor: '#DFDFDF',
        paddingVertical: height * 0.025,
        paddingHorizontal: width * 0.025,
    },
    balance: {
        marginLeft: width * 0.01,
        textAlign: 'justify',
        fontSize: 14
    },
    balanceText: {
        color: '#2DB38D',
        textAlign: 'right',
        fontWeight: 'bold'
    },
    lastYear: {
        fontSize: 10,
        textAlign: 'left',
        marginLeft: width * 0.01
    }, 
    lastYearText: {
        fontSize: 10,
        color: '#A18DB5',
        textAlign: 'right'
    },
    lastBlockView: {
        flexDirection: 'row',
        paddingTop: height * 0.025,
        paddingHorizontal: width * 0.025
    },
    scheduledValue: {
        color: '#A18DB5'
    }
})

export default BalanceView;