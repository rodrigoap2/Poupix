import React, { useEffect, useState } from 'react'
import {Text, View, StyleSheet, Image, Dimensions} from 'react-native'
import MapView from 'react-native-maps';

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const MapImage = ({coordinates}) => {

    return (
        <View>
            <MapView
                initialRegion={{
                latitude: coordinates.lat,
                longitude: coordinates.lon,
                latitudeDelta: 0.025,
                longitudeDelta: 0.025,
                }}
                style={{width: width * 1, height: height * 0.4}}
            />
        </View>
    );
}

export default MapImage;