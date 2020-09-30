import React, {useContext, useEffect, useState} from 'react'
import {Text, View, StyleSheet, ScrollView} from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context';

import Spacer from '../components/Spacer';
import MenuView from '../components/MenuView'
import {Context as StoresContext} from '../context/StoresContext'
import MenuStores from '../components/MenuStores';
import MenuGoals from '../components/MenuGoals';
import MenuMicroInvesting from '../components/MenuMicroInvesting'; 
import ShopIconSvg from '../../assets/img/ShopIconSvg'
import MicroInvestingIconSvg from '../../assets/img/MicroInvestingIconSvg'
import GoalsIconSvg from '../../assets/img/GoalsIconSvg'
import { FlatList } from 'react-native-gesture-handler';
import poupixApi from '../api/poupixApi'

const MenuScreen = ({navigation}) => {
    const name = 'Rodrigo'
    const {state, getStores} = useContext(StoresContext)
    const [goals, setGoals] = useState({})
    const [carregou, setCarregou] = useState(false)
    const getGoals = () => {
        return () => {
            try{
                //const response = await poupixApi.get('/stores')
                const goals = {
                    'roundup': {
                        'lastMonth': 30,
                        'total': 30000.20,
                        'valuePerTransaction': 1
                    },
                    'goals': {
                        'thisMonthPercentage':0.7,
                        'thisMonthTotalValue': 872.23,
                        'goalsInfo': [
                            {
                                'name': 'Casa',
                                'totalValue': 400119.99,
                                'actualValue': 200059.99,
                                'actualMonth': 23,
                                'totalMonths': 100
                            },
                            {
                                'name': 'Computador',
                                'totalValue': 4199.99,
                                'actualValue': 3295.99,
                                'actualMonth': 23,
                                'totalMonths': 100
                            },
                        ]
                    },
                    'account': {
                        'balance': 202119.98,
                        'lastYear': 10709.50,
                        'revenue': 251.79,
                        'revenueIndex': '100% do CDI',
                        'scheduled': 20.40
                    }
                }
                return goals
            }catch(err){
                console.log('Erro na requisição das metas')
                return -1
            }
        };
    }
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
    useEffect(() => {
        getStores()
        setGoals(getGoals())
        setCarregou(true)
    },[])

    console.disableYellowBox = true;
    if(carregou){
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
                    component = {<MenuGoals goals={goals.goals.goalsInfo.slice(0,2)} savings={goals.goals.thisMonthTotalValue} savingsPercentage={goals.goals.thisMonthPercentage}/>}
                    />
                    <Spacer/>
                    <MenuMicroInvesting
                    onPress = {() => navigation.navigate('MicroInvesting')}
                    microInvestingValue={`R$${formatNumber(goals.roundup.total)}`}
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