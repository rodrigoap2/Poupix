import React from 'react'
import { StyleSheet, View, ImageBackground, Image } from 'react-native';
import { Text } from 'react-native-elements';
import {TouchableOpacity} from 'react-native-gesture-handler'
import { navigate } from '../navigationRef';

const StoreDescription = ({store, navigation}) => {
    return(
        <View style={styles.container}>
            <TouchableOpacity style={styles.touchable} activeOpacity={.2} onPress={() => navigation.navigate('StoreDetail', {id: store.id})}>
                <Image
                    source={{uri: store.pictures}}
                    style={styles.imageStyle}
                />
                <View style={styles.infoStyle}>
                    <View>
                        <Text style={styles.storeName}>{store.name}</Text>
                        <Text style={styles.type}>{store.type}</Text>
                        <Text style={styles.distance}>{store.distance}</Text>
                    </View>
                </View>
                <View>
                    <ImageBackground
                        source={{uri: 'https://i.imgur.com/vKeMOMA.png'}}
                        style={styles.semicircle}
                    >
                    <Text style={styles.cashback}>{store.cashback}</Text>
                    </ImageBackground>
                </View>
            </TouchableOpacity>
        </View>
       
    )
}

const styles = StyleSheet.create({
    container: {
        borderColor: '#707070',
        borderRadius: 10,
        margin: '2%',
        backgroundColor: '#FFFFFF',
        elevation: 3,
        shadowOffset:{  width: 1,  height: 1,  },
        shadowColor: '#AAA',
        shadowOpacity: 1.0,
    },
    touchable: {
        flexDirection: 'row'
    },
    imageStyle: {
        top: 0,
        bottom: 0,
        width: 80,
        height: 80,
        marginRight: 20,
        borderTopLeftRadius: 10,
        borderBottomLeftRadius: 10,
        alignSelf: 'flex-start',
    },
    storeName: {
        fontWeight: 'bold',
        fontSize: 18
    },
    type: {
        fontSize: 12,
        marginBottom: '20%'
    },
    cashback: {
        color: '#FFFFFF',
        marginTop: '3%',
        marginRight: '10%',
        fontStyle: 'italic',
        fontWeight: 'bold',
        paddingLeft: '25%',
        paddingBottom: '20%',
        position: 'absolute'
    },
    distance : {
        color: '#A18DB5',
        bottom: 0,
    },
    infoStyle : {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'space-between'
    },
    semicircle : {
        width: 47,
        height: 40,
        alignSelf: 'flex-start',
        justifyContent: 'center',
        alignItems: 'center',
        right: 0
    }

})

export default StoreDescription;