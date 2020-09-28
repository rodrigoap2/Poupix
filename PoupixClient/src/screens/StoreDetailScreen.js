import React, {useContext, useEffect} from 'react'
import {Text, View, StyleSheet, Image, ScrollView, Dimensions } from 'react-native'
import { BarChart, Grid, XAxis, YAxis } from 'react-native-svg-charts'
import { VictoryBar, VictoryChart, VictoryAxis} from "victory-native";
import PicturesList from '../components/PicturesList';
import MapImage from '../components/MapImage';

import {Context as StoresContext} from '../context/StoresContext'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const StoreDetailScreen = ({navigation, id}) => {
    const {state, getOneStore} = useContext(StoresContext)
    
    useEffect(() => {
        getOneStore(id)
    },[])

    if(!state.detailedStore.pictures){
        return(
            <View>
            </View>
        )
    }else{
          
        const data = [state.detailedStore.cashback.monday*100 + "%", state.detailedStore.cashback.tuesday*100 + "%", state.detailedStore.cashback.wednesday*100 + "%", state.detailedStore.cashback.thursday*100 + "%", state.detailedStore.cashback.friday*100 + "%", state.detailedStore.cashback.saturday*100 + "%", state.detailedStore.cashback.sunday*100 + "%"]
        const data3 = [{
            y: state.detailedStore.cashback.monday,
            x: "Seg",
        },
        {
            y: state.detailedStore.cashback.tuesday,
            x: "Ter",
        },
        {
            y: state.detailedStore.cashback.wednesday,
            x: "Qua",
        },
        {
            y: state.detailedStore.cashback.thursday,
            x: "Qui",
        },
        {
            y: state.detailedStore.cashback.friday,
            x: "Sex",
        },
        {
            y: state.detailedStore.cashback.saturday,
            x: "Sáb",
        },
        {
            y: state.detailedStore.cashback.sunday,
            x: "Dom",
        },]

        return(
            <View style={styles.container}>
                <ScrollView style={{flex: 1}}>
                <Image
                        source={{uri: state.detailedStore.pictures[0]}}
                        style={styles.imageStyle}
                />
                <View style={styles.infoView}>
                    <Text style={styles.title}>{state.detailedStore.name}</Text>
                    <View style={styles.cashbackView}>
                        <Text style={styles.cashback}>{state.detailedStore.cashback.friday * 100}%  Hoje!</Text>
                        <Text style={styles.distance}>À 50m</Text>
                    </View>
                </View>
                <View style={styles.description}>
                    <Text style={styles.descriptionText}>{state.detailedStore.description}</Text>
                </View>
                <View style={styles.cashbackGraph}>
                    <Text style={styles.cashbackGraphText}>Valores de Cashback</Text>
                    <View style={{alignItems: 'center'}}>
                        <VictoryChart height={height * 0.25}>
                            <VictoryBar data={data3} labels={data} cornerRadius={5} barRatio={1} domainPadding={{x: 250}} standalone={false} style={{data: { fill: "#AC69F1" }, labels: {fill: '#8516FA'}, parent: { borderColor: '#8516FA' }}}/>
                            <VictoryAxis/>
                        </VictoryChart>
                    </View>
                </View>
                <View style={styles.picturesView}>
                    <PicturesList
                        images={state.detailedStore.pictures}
                    />
                </View>
                <View style={styles.mapView}>
                    <Text style={styles.addressText}>{state.detailedStore.address}</Text>
                    <MapImage
                        coordinates={state.detailedStore.coordinates}
                    />
                </View>
                </ScrollView>
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
        marginLeft: width * 0.05,
        fontWeight: 'bold'
    },
    imageStyle: {
        width: width * 1,
        aspectRatio: 1.4,
        resizeMode:'cover',
        marginBottom: height * 0.02
    },
    cashback: {
        color: '#8F2BFA',
        fontWeight: 'bold',
        textAlign: 'right'
    },
    infoView: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginBottom: height * 0.02
    },
    cashbackView: {
        marginRight: width * 0.05,
    },
    description: {
        marginBottom: height * 0.01
    },
    descriptionText: {
        textAlign: 'justify',
        marginRight: width * 0.05,
        marginLeft: width * 0.05,
        fontSize: 14
    },
    cashbackGraph: {
        marginLeft: width * 0.05,
    },
    cashbackGraphText: {
        fontWeight: 'bold',
        fontSize: 12
    },
    distance: {
        textAlign: 'right'
    },
    mapView: {

    },
    addressText: {
        marginLeft: width * 0.05,
        marginBottom: height * 0.02,
        textAlign: 'left',
    }
});

export default StoreDetailScreen;