import React, {useContext, useEffect} from 'react'
import {Text, View, StyleSheet, Image } from 'react-native'
import {Context as StoresContext} from '../context/StoresContext'

const StoreDetailScreen = ({navigation, id}) => {
    const {state, getOneStore} = useContext(StoresContext)
    const data = {
        labels: ["Segunda", "Terca", "Quarta", "Quinta", "Sexta", "Sabado", "Domingo"],
        datasets: [
          {
            data: [state.detailedStore.cashback.monday, state.detailedStore.cashback.tuesday, state.detailedStore.cashback.wednesday, state.detailedStore.cashback.thursday, state.detailedStore.cashback.friday, state.detailedStore.cashback.saturday, state.detailedStore.cashback.sunday]
          }
        ]
      };
    useEffect(() => {
        getOneStore(id)
    },[])

    if(!state.detailedStore.pictures){
        return(
            <View>
            </View>
        )
    }else{
        return(
            <View style={styles.container}>
                <Image
                        source={{uri: state.detailedStore.pictures[1]}}
                        style={styles.imageStyle}
                />
                <View style={styles.infoView}>
                    <Text style={styles.title}>{state.detailedStore.name}</Text>
                    <View style={styles.cashbackView}>
                        <Text style={styles.cashback}>{state.detailedStore.cashback.friday * 100}%  Hoje!</Text>
                        <Text>À 50m</Text>
                    </View>
                </View>
                <View style={styles.description}>
                    <Text>{state.detailedStore.description}</Text>
                </View>
                <View style={styles.cashbackGraph}>
                    <Text style={styles.cashbackGraphText}>Valores de Cashback</Text>
                </View>
            </View>
        )
    }
}

StoreDetailScreen.navigationOptions = () => {
    return{
        header: () => false
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#FAFAFA',
    },
    title: {
        fontSize: 20,
        marginLeft: '5%',
        fontWeight: 'bold'
    },
    imageStyle: {
        flex: 3,
        resizeMode:'cover',
        marginBottom: '5%'
    },
    cashback: {
        color: '#8F2BFA',
        fontWeight: 'bold',
    },
    infoView: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'space-between',
    },
    cashbackView: {
        marginRight: '5%'
    },
    description: {
        flex: 2,
        alignSelf: 'center'
    },
    cashbackGraph: {
        flex: 3,
        marginLeft: '5%',
    },
    cashbackGraphText: {
        fontWeight: 'bold',
    }
});

export default StoreDetailScreen;