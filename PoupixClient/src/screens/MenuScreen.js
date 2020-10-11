import React, {useContext, useEffect, useState} from 'react'
import {Text, View, StyleSheet, ScrollView, Button} from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context';

import Spacer from '../components/Spacer';
import MenuView from '../components/MenuView'
import {Context as StoresContext} from '../context/StoresContext'
import MenuStores from '../components/MenuStores';
import MenuGoals from '../components/MenuGoals';
import MenuMicroInvesting from '../components/MenuMicroInvesting'; 
import ShopIconSvg from '../../assets/img/ShopIconSvg'
import GoalsIconSvg from '../../assets/img/GoalsIconSvg'
import getGoals from '../functions/getGoals'
import formatNumber from '../functions/formatNumber'
import SignOutButton from '../components/SignOutButton'
import poupixApi from '../api/poupixApi'

const MenuScreen = ({navigation}) => {
    const name = 'Rodrigo'
    const {state, getStores} = useContext(StoresContext)
    const [goals, setGoals] = useState({})
    const [carregou, setCarregou] = useState(false)

    useEffect(() => {
        getStores()
        poupixApi.get('/accounts/general-information').then((res) => setGoals(res.data)).catch((err) => console.log(err))
        setCarregou(true)
    },[])

    console.disableYellowBox = true;
    if(carregou){
        console.log(goals)
        return(
            <View style={styles.container}>
                <SafeAreaView style={styles.topView}>
                    <Text style={styles.title}>Olá, <Text style={{fontWeight:'bold'}}>{name}</Text> </Text>
                </SafeAreaView>
                <ScrollView>
                    <Spacer/>
                    <MenuView
                    onPress = {() => navigation.navigate('Goals', {name: name, goals: goals})}
                    title = 'Metas'
                    image = {<GoalsIconSvg/>}
                    buttonText = 'Detalhes'
                    component = {goals.goals ? <MenuGoals goals={goals.goals.goalsInfo.slice(0,2)} savings={goals.goals.thisMonthProgress} savingsPercentage={goals.goals.thisMonthPercentage}/> : <View></View>}
                    />
                    <Spacer/>
                    <MenuMicroInvesting
                    onPress = {() => navigation.navigate('MicroInvesting', {name: name, goals: goals})}
                    microInvestingValue={goals.roundup ? `R$${formatNumber(goals.roundup.total)}` : 0}
                    />
                    <Spacer/>
                    <MenuView
                    onPress = {() => navigation.navigate('Lojas', { stores: state.stores }) }
                    title = 'Lojas em destaque'
                    image = {<ShopIconSvg/>}
                    buttonText = 'Ver mais' 
                    component = {<MenuStores stores={state.stores.slice(0,3)} />} 
                    />
                    <SignOutButton/>
                </ScrollView> 
            </View>
        )
    }else{
        return(
            <View>

            </View>
        );
    }
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