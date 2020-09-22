import React, { useState } from 'react';
import { StyleSheet, View, KeyboardAvoidingView} from 'react-native';
import { Text, Button, Input } from 'react-native-elements';
import Spacer from '../components/Spacer';

const LoginScreen = ({navigation}) => {
    const [cpf, setCpf] = useState('');
    const [password, setPassword] = useState('');

    const validateCpf = (cpf) => {
        if (/^\d+$/.test(cpf) || cpf == '') {
          setCpf(cpf)
        }
    }

    return(
        <View style={styles.container}>
            <Spacer>
                <Text style={styles.title}>Poupix</Text>
            </Spacer>
            <View style={styles.inputView}>
                <Spacer/>
                <Spacer>
                    <Input
                        label="CPF"
                        value={cpf}
                        onChangeText={validateCpf}
                        autoCapitalize="none"
                        autoCorrect={false}
                        keyboardType={'numeric'}
                        maxLength={11}
                    />
                </Spacer>
                <Spacer>
                    <Input
                        secureTextEntry
                        label="Password"
                        value={password}
                        onChangeText={setPassword}
                        autoCapitalize="none"
                        autoCorrect={false}
                    />
                </Spacer>
                <Spacer>
                    <Button
                    title="Entrar"
                    buttonStyle={styles.signInButton}
                    onPress={() => navigation.navigate('Menu')}
                    />
                </Spacer>
            </View>
        </View>
    )
}

LoginScreen.navigationOptions = () => {
    return {
      header: () => false
    };
}

const styles = StyleSheet.create({
    title: {
        textAlign: "center",
        color: '#FFFFFF',
        fontSize: 40,
        top: -100
    },
    container: {
        backgroundColor: '#FFB726',
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'stretch',
    },
    inputView: {
        backgroundColor: '#FFFFFF',
        borderRadius: 10,
        borderWidth: 1,
        borderColor: '#FFFFFF',
        marginHorizontal: 15
    },
    signInButton: {
        backgroundColor: '#8516FA',
        overflow: 'hidden',
        borderRadius: 10,
        marginHorizontal: 100,
    }
});

export default LoginScreen;