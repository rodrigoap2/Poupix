import React from 'react'
import { StyleSheet, View, Text, Dimensions, TouchableOpacity } from 'react-native';

import ProgressCircle from 'react-native-progress-circle'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const MenuGoals = ({goals, savings, savingsPercentage}) => {

    return (
        <View>
            <View style={styles.progressBarView}>
                <View style={{marginRight: width * 0.1}}>
                    <ProgressCircle
                    percent={Math.round((goals[0].actualValue/goals[0].totalValue*100))}
                    radius={40}
                    borderWidth={8}
                    color="#8516FA"
                    shadowColor="#DFDFDF"
                    bgColor="#fff">
                        <Text style={{color: "#8516FA", fontWeight:'bold', fontStyle: 'italic'}}>{Math.round((goals[0].actualValue/goals[0].totalValue*100))}%</Text>
                    </ProgressCircle>
                    <Text style={styles.goalTitle}>{goals[0].name}</Text>
                </View>
                <View style={{marginLeft: width * 0.1}}>
                    <ProgressCircle
                    percent={Math.round((goals[1].actualValue/goals[1].totalValue*100))}
                    radius={40}
                    borderWidth={8}
                    color="#8516FA"
                    shadowColor="#DFDFDF"
                    bgColor="#fff">
                        <Text style={{color: "#8516FA", fontWeight:'bold', fontStyle: 'italic'}}>{Math.round((goals[1].actualValue/goals[1].totalValue*100))}%</Text>
                    </ProgressCircle>
                    <Text style={styles.goalTitle}>{goals[1].name}</Text>
                </View>
            </View>
            <View style={styles.savingsView}>
                <Text style={styles.savingsPercentageText}>Você atingiu <Text style={styles.savingsPercentage}>{savingsPercentage*100}%</Text> do seu {'\n'} objetivo mensal</Text>
                <Text style={styles.savingsText}>Suas economias esse {'\n'} mês somam <Text style={styles.savings}>R$ {savings}</Text></Text>
            </View>
        </View>
    );

}

const styles = StyleSheet.create({
    progressBarView: {
        flexDirection: "row",
        justifyContent: "center"
    },
    goalTitle: {
        textAlign: 'center',
        fontStyle: 'italic',
        marginTop: height * 0.01
    },
    savingsView: {
        flexDirection: "row",
        justifyContent: "center",
        marginTop: height * 0.01
    },
    savingsPercentageText: {
        color: '#707070',
        fontSize: 11,
        textAlign: 'center',
        marginRight: width * 0.05
    },
    savingsPercentage: {
        color: '#64B330',
        fontWeight: 'bold'
    },
    savings: {
        color: '#64B330',
        fontWeight: 'bold'
    },
    savingsText: {
        color: '#707070',
        textAlign: 'center',
        fontSize: 11,
        marginLeft: width * 0.05
    }
});

export default MenuGoals;