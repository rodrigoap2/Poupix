import React, { useState, useContext } from 'react';
import { StyleSheet, View } from 'react-native';
import { Text, Button, Input } from 'react-native-elements';
import Spacer from '../components/Spacer';
import { Context as AuthContext } from '../context/AuthContext'
import { NavigationEvents } from 'react-navigation';

const LoginScreen = ({navigation}) => {
    const [cpf, setCpf] = useState('');
    const [password, setPassword] = useState('');
    const { state, signIn, clearErrorMessage } = useContext(AuthContext);

    const validateCpf = (cpf) => {
        if (/^\d+$/.test(cpf) || cpf == '') {
          setCpf(cpf)
        }
    }

    return(
        <View style={styles.container}>
            <NavigationEvents onWillFocus={clearErrorMessage} />
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
                {state.errorMessage ? <Text style={styles.errorMessage}>{state.errorMessage}</Text> : null}
                <Spacer>
                    <Button
                    title="Entrar"
                    buttonStyle={styles.signInButton}
                    onPress={() => signIn({cpf, password})}
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
    },
    errorMessage: {
        fontSize: 16,
        color: 'red',
        textAlign: 'center'
    }
});

export default LoginScreen;