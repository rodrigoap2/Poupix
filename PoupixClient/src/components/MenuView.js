import React from 'react'
import { TouchableOpacity } from 'react-native-gesture-handler';
import {Text, View, StyleSheet, Dimensions} from 'react-native'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const MenuView = ({onPress, title, buttonText, component, image}) => {
    return (
        <TouchableOpacity onPress={onPress}>
                    <View style={styles.objectView}>
                        <View style={styles.headerView}>
                            <View style={styles.image}>
                                {image}
                            </View>
                            <Text style={styles.title}>{title}</Text>
                        </View>
                        {component}
                        <Text style={styles.navigationButton}>{buttonText}</Text>
                    </View>
        </TouchableOpacity>
    )

}

const styles = StyleSheet.create({
    objectView: {
        backgroundColor: '#FFFFFF',
        borderRadius: 10,
        borderWidth: 1,
        borderColor: '#DCDCDC',
        marginHorizontal: 15
    },
    navigationButton : {
        color: '#8F2BFA',
        textAlign: 'center',
        fontWeight: 'bold',
        marginTop: '3%',
        marginBottom: '5%',
        fontSize: 14
    },
    title : {
        color: '#434343',
        fontStyle: 'italic',
    },
    headerView : {
        flexDirection: 'row',
        marginTop: '5%',
        marginLeft: '5%',
        marginBottom: '7%',
    },
    image: {
        width: width * 0.05,
        height: height * 0.02, 
        marginRight: '2%',
        marginTop: '1%'
    }
});

export default MenuView;