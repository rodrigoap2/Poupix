import React from 'react'
import {Text, View, StyleSheet, Image, Dimensions} from 'react-native'
import { FlatList } from 'react-native-gesture-handler'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const PicturesList = ({images}) => {
    return(
        <View>
            <FlatList
                data={images}
                keyExtractor={(result) => result}
                horizontal
                showsHorizontalScrollIndicator={false}
                renderItem={( {item} ) => {
                    return(
                    <Image
                        source={{uri: item}}
                        style={styles.imageStyle}
                    />
                    );
                }}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    imageStyle: {
        width: width * 0.5,
        height: height * 0.2,
        marginLeft: width * 0.03,
        marginRight: width * 0.02,
        resizeMode: 'stretch',
        marginBottom: height * 0.05,
    }
});

export default PicturesList;