import React, {useState, useEffect} from 'react'
import {View, StyleSheet, Dimensions, ScrollView} from 'react-native'
import ScreenHeader from '../components/ScreenHeader'
import poupixApi from '../api/poupixApi'
import { SafeAreaView } from 'react-navigation';
import {navigate} from '../navigationRef'
import { Text, Input } from 'react-native-elements';
import { TouchableOpacity } from 'react-native-gesture-handler';

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height;

const CreateGoalScreen = () => {
    const [title, setTitle] = useState('')
    const [value, setValue] = useState('')
    const [months, setMonths] = useState('')

    const createGoal = async () => {
        await poupixApi.post('/accounts/goal', {title: title, totalGoal: value, totalMonths: months})
        navigate('Goals')
    }

    return (
        <View style={{flex: 1, backgroundColor: '#FFFFFF'}}>
            <SafeAreaView style={{backgroundColor: '#FFB726', paddingBottom: height * 0.03}}>
                <View style={{marginTop: height * 0.06}}>
                    <ScreenHeader title="Nova meta" onPress={() => navigate('Goals')}/>
                </View>
            </SafeAreaView>
            <ScrollView style={styles.inputView}>
                <Input
                    label="Nome da meta"
                    value={title}
                    onChangeText={(text) => setTitle(text)}
                    autoCapitalize="none"
                    autoCorrect={false}
                />
                <View style={{flexDirection: 'row', marginRight: width * 0.4}}>
                    <Input
                        label="Valor"
                        value={`${value}`}
                        onChangeText={(text) => setValue(text)}
                        autoCapitalize="none"
                        leftIcon={<Text>R$</Text>}
                        autoCorrect={false}
                        keyboardType={'numeric'}
                    />
                    <Input
                        label="Período"
                        value={`${months}`}
                        onChangeText={(text) => setMonths(text)}
                        autoCapitalize="none"
                        rightIcon={<Text>meses</Text>}
                        containerStyle={{marginRight: width * 0.1}}
                        autoCorrect={false}
                        keyboardType={'numeric'}
                    />
                </View>
                <View style={styles.optionsView}>
                    <Text style={{textAlign:'center', fontWeight: 'bold', fontSize: height * 0.02, color: '#8516FA'}}>Sugestões de metas</Text>
                    <View style={styles.optionsRow}>
                        <TouchableOpacity style={styles.optionButton} onPress={() => {setTitle('Casa'); setValue('500000'); setMonths('240')}}>
                            <Text style={styles.buttonText}>Carro</Text>
                        </TouchableOpacity>
                        <TouchableOpacity style={styles.optionButton} onPress={() => {setTitle('Carro'); setValue('40000'); setMonths('72')}}>
                            <Text style={styles.buttonText}>Casa</Text>
                        </TouchableOpacity>
                    </View>
                    <View style={styles.optionsRow}>
                        <TouchableOpacity style={styles.optionButton} onPress={() => {setTitle('Viagem'); setValue('5000'); setMonths('48')}}>
                            <Text style={styles.buttonText}>Viagem</Text>
                        </TouchableOpacity>
                        <TouchableOpacity style={styles.optionButton} onPress={() => {setTitle('Computador'); setValue('2500'); setMonths('24')}}>
                            <Text style={styles.buttonText}>Computador</Text>
                        </TouchableOpacity>
                    </View>
                    <View style={styles.optionsRow}>
                        <TouchableOpacity style={styles.optionButton} onPress={() => {setTitle('Televisão'); setValue('2000'); setMonths('18')}}>
                            <Text style={styles.buttonText}>Televisão</Text>
                        </TouchableOpacity>
                        <TouchableOpacity style={styles.optionButton} onPress={() => {setTitle('Aposentadoria'); setValue('1000000'); setMonths('360')}}>
                            <Text style={styles.buttonText}>Aposentadoria</Text>
                        </TouchableOpacity>
                    </View>
                </View>
                <TouchableOpacity style={styles.createGoalButton} onPress={() => createGoal()}>
                    <Text style={styles.createGoalText}>Criar Meta</Text>
                </TouchableOpacity>
            </ScrollView>
        </View>
    );
}

CreateGoalScreen.navigationOptions = () => {
    return{
        header: () => false
    }
}

const styles = StyleSheet.create({
    inputView: {
        marginTop: height * 0.1,
        marginHorizontal: width * 0.1,
        backgroundColor: '#FFFFFF'
    },
    optionsView: {

    },
    optionsRow: {
        flexDirection: 'row', 
        justifyContent: 'space-between', 
        marginHorizontal: width * 0.125
    },
    optionButton: {
        backgroundColor: '#FFFFFF',
        overflow: 'hidden',
        borderRadius: 10,
        width: width * 0.25,
        height: height * 0.05,
        justifyContent: 'center',
        alignSelf: 'center',
        marginVertical: height * 0.02,
        elevation: 3,
        shadowOffset:{  width: 1,  height: 1,  },
        shadowColor: '#AAA',
        shadowOpacity: 1.0,
    },
    buttonText: {
        textAlign: 'center',
        color: '#8516FA',
        fontWeight: 'bold'
    },
    createGoalButton: {
        backgroundColor: '#8516FA',
        overflow: 'hidden',
        borderRadius: 10,
        width: width * 0.3,
        height: height * 0.05,
        justifyContent: 'center',
        alignContent: 'center',
        alignSelf: 'center',
        marginTop: height * 0.075
    },
    createGoalText: {
        textAlign: 'center',
        color: '#FFFFFF',
        fontWeight: 'bold'
    }
});

export default CreateGoalScreen;