import React from 'react'
import { StyleSheet, View, Image } from 'react-native';
import { Text } from 'react-native-elements';

const SmallStoreDescription = ({store}) => {
    return(
        <View style={styles.container}>
            <Image
                source={{uri: store.image}}
                style={styles.imageStyle}
            />
            <View style={styles.infoStyle}>
                <View>
                    <Text style={styles.storeName}>{store.name}</Text>
                    <Text style={styles.distance}>{store.distance} distante</Text>
                </View>
                <Text style={styles.cashback}>{store.cashback}</Text>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: 'row',
    },
    imageStyle: {
        width: 35,
        height: 35,
        marginLeft: '10%',
        marginRight: '5%',
        marginBottom: '5%'
    },
    storeName: {
        fontWeight: 'bold'
    },
    cashback: {
        color: '#8F2BFA',
        marginTop: '3%',
        marginRight: '10%',
        fontStyle: 'italic',
    },
    distance : {
        color: '#A18DB5'
    },
    infoStyle : {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'space-between',
    }
});

export default SmallStoreDescription;