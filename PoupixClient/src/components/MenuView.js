import React from 'react'
import { TouchableOpacity } from 'react-native-gesture-handler';
import {Text, View, StyleSheet} from 'react-native'

const MenuView = ({onPress, title, buttonText, component}) => {

    return (
        <TouchableOpacity onPress={onPress}>
                    <View style={styles.objectView}>
                        <Text style={styles.title}>{title}</Text>
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
        marginTop: '5%',
        marginLeft: '5%',
        marginBottom: '7%',
        color: '#A18DB5',
        fontStyle: 'italic'
    }
});

export default MenuView;