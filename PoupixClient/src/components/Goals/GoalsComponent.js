import React from 'react'
import {Text, View, StyleSheet, Dimensions} from 'react-native'
import GoalsIconSvg from '../../../assets/img/GoalsIconSvg'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const GoalsComponent = () => {
    return (
        <View>
            <View style={styles.headerView}>
                <View style={styles.image}>
                    <GoalsIconSvg/>
                </View>
                <Text style={styles.title}>Metas</Text>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
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
    },
    title : {
        color: '#434343',
        fontStyle: 'italic',
    },
});

export default GoalsComponent;