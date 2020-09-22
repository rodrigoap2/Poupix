import React from 'react'
import {Text, View, StyleSheet} from 'react-native'
import {Button} from 'react-native-elements'
import { TouchableOpacity } from 'react-native-gesture-handler';
import { SafeAreaView } from 'react-native-safe-area-context';
import Spacer from '../components/Spacer';


const MenuScreen = ({navigation}) => {
    const name = 'Rodrigo'
    return(
        <View style={styles.container}>
            <SafeAreaView style={styles.topView}>
                <Text style={styles.title}>Olá, <Text style={{fontWeight:'bold'}}>{name}</Text> </Text>
            </SafeAreaView>
            <View>
                <Spacer/>
                <TouchableOpacity onPress={() => navigation.navigate('Goals')}>
                    <View style={styles.objectView}>
                        <Text>Metas</Text>
                        <Text style={styles.navigationButton}>Detalhes</Text>
                    </View>
                </TouchableOpacity>
                <Spacer/>
                <TouchableOpacity onPress={() => navigation.navigate('MicroInvesting')}>
                    <View style={styles.objectView}>
                        <Text>Micro-investimento</Text>
                        <Text style={styles.navigationButton}>Ajustar</Text>
                    </View>
                </TouchableOpacity>
                <Spacer/>
                <TouchableOpacity onPress={() => navigation.navigate('Stores')}>
                    <View style={styles.objectView}>
                        <Text>Lojas em destaque</Text>
                        <Text style={styles.navigationButton}>Ver mais</Text>
                    </View>
                </TouchableOpacity>
            </View>
        </View>
    )
}

MenuScreen.navigationOptions = () => {
    return{
        header: () => false
    }
}

const styles = StyleSheet.create({
    title: {
        fontSize: 24,
        color: '#FFFFFF',
        textAlign: 'center'
    },
    topView: {
        backgroundColor: '#FFB726',
        paddingBottom: 30,
    },
    objectView: {
        backgroundColor: '#FFFFFF',
        borderRadius: 10,
        borderWidth: 1,
        borderColor: '#DCDCDC',
        marginHorizontal: 15
    }, 
    container: {
        backgroundColor: '#FFFFFF', 
        flex: 1
    },
    navigationButton:{
        color: '#8F2BFA',
        textAlign: 'center',
        fontWeight: 'bold',
        fontSize: 14
    }
});

export default MenuScreen;