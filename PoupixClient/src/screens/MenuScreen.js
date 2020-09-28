import React, {useContext, useEffect} from 'react'
import {Text, View, StyleSheet, ScrollView} from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context';

import Spacer from '../components/Spacer';
import MenuView from '../components/MenuView'
import {Context as StoresContext} from '../context/StoresContext'
import MenuStores from '../components/MenuStores';
import MenuMicroInvesting from '../components/MenuMicroInvesting'; 
import ShopIconSvg from '../../assets/img/ShopIconSvg'
import MicroInvestingIconSvg from '../../assets/img/MicroInvestingIconSvg'
import GoalsIconSvg from '../../assets/img/GoalsIconSvg'
import { FlatList } from 'react-native-gesture-handler';

const MenuScreen = ({navigation}) => {
    const name = 'Rodrigo'
    const {state, getStores} = useContext(StoresContext)

    useEffect(() => {
        getStores()
    },[])

    return(
        <View style={styles.container}>
            <SafeAreaView style={styles.topView}>
                <Text style={styles.title}>Olá, <Text style={{fontWeight:'bold'}}>{name}</Text> </Text>
            </SafeAreaView>
            <ScrollView>
                <Spacer/>
                <MenuView
                onPress = {() => navigation.navigate('Goals')}
                title = 'Metas'
                image = {<GoalsIconSvg/>}
                buttonText = 'Detalhes'
                component = {<Text>aaa</Text>}
                />
                <Spacer/>
                <MenuMicroInvesting
                microInvestingValue={'R$ 1458,25'}
                />
                <Spacer/>
                <MenuView
                onPress = {() => navigation.navigate('Lojas', { stores: state.stores }) }
                title = 'Lojas em destaque'
                image = {<ShopIconSvg/>}
                buttonText = 'Ver mais' 
                component = {<MenuStores stores={state.stores.slice(0,3)} />} 
                />
            </ScrollView> 
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
        textAlign: 'center',
        marginTop: '5%'
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