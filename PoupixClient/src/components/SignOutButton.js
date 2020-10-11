import React, {useContext} from 'react'
import { TouchableOpacity } from 'react-native-gesture-handler';
import {Text, View, StyleSheet, Dimensions} from 'react-native'
import { Context as AuthContext } from '../context/AuthContext'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const SignOutButton = () => {
    const { signOut } = useContext(AuthContext);

    return (
        <TouchableOpacity onPress={() => signOut()} style={styles.logoutButton}>
            <Text style={styles.textStyle}>Log out</Text>
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    logoutButton: {
        backgroundColor: '#8516FA',
        overflow: 'hidden',
        borderRadius: 10,
        width: width * 0.2,
        height: height * 0.05,
        justifyContent: 'center',
        alignSelf: 'center',
        marginVertical: height * 0.02
    }, 
    textStyle: {
        color: 'white',
        textAlign: 'center'
    }
})

export default SignOutButton;