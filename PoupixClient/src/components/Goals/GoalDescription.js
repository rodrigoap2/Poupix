import React from 'react'
import {Text, View, StyleSheet, Dimensions} from 'react-native'
import ProgressCircle from 'react-native-progress-circle'
import formatNumber from '../../functions/formatNumber'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const GoalDescription = ({goal}) => {

    return (
            <View style={styles.objectView}>
                <View style={{flexDirection: 'row'}}>
                    <View>
                        <Text style={styles.name}>{goal.name}</Text>
                        <View style={styles.progressBar}>
                            <ProgressCircle
                                percent={Math.round((goal.actualValue/goal.totalValue*100))}
                                radius={width * 0.15}
                                borderWidth={width * 0.03}
                                color="#8516FA"
                                shadowColor="#DFDFDF"
                                bgColor="#fff">
                                    <Text style={{color: "#8516FA", fontWeight:'bold', fontStyle: 'italic'}}>{Math.round((goal.actualValue/goal.totalValue*100))}%</Text>
                            </ProgressCircle>
                        </View>
                    </View>
                    <View style={styles.goalInfo}>
                        <Text style={styles.actualValue}>R${formatNumber(goal.actualValue)}</Text>
                        <Text style={styles.totalValue}>de R${formatNumber(goal.totalValue)}</Text>
                        <View style={styles.actualMonthView}>
                            <Text style={styles.actualMonthText}>Mês atual</Text>
                            <Text style={styles.actualMonth}>{goal.actualMonth}</Text>
                        </View>
                        <View style={styles.totalMonthsView}>
                            <Text style={styles.actualMonthText}>Total de meses</Text>
                            <Text style={styles.actualMonth}>{goal.totalMonths}</Text>
                        </View>
                    </View>
                </View>
            </View>
        )
}

const styles = StyleSheet.create({
    objectView: {
        alignSelf: 'center',
        backgroundColor: '#FFFFFF',
        borderRadius: 20,
        borderWidth: 1,
        borderColor: '#DCDCDC',
        width: width * 0.7,
        height: height * 0.25,
        elevation: 3,
        shadowOffset:{  width: 1,  height: 1 },
        shadowColor: '#AAA',
        shadowOpacity: 1.0,
    },
    name: {
        fontSize: 18,
        fontWeight: 'bold',
        fontStyle: 'italic',
        paddingHorizontal: width * 0.05,
        paddingVertical: height * 0.01,
    },
    progressBar: {
        marginLeft: width * 0.05,
        marginTop: height * 0.01
    },
    actualValue: {
        color: '#64B330',
        fontWeight: 'bold',
        fontSize: height * 0.021,
        marginTop: height * 0.05,
        textAlign: 'right',
    },
    totalValue: {
        textAlign: 'right',
        color: '#B7B7B7',
        fontSize: height * 0.016,
    },
    goalInfo: {
        marginLeft: width * 0.02,
    },
    actualMonth: {
        fontSize: height * 0.0135,
        textAlign: 'right',
        color: '#A18DB5'
    },
    actualMonthText: {
        fontSize: height * 0.0135,
        marginRight: width * 0.01
    },
    actualMonthView: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginTop: height * 0.07
    },
    totalMonthsView: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        flex: 1
    }
});

export default GoalDescription;