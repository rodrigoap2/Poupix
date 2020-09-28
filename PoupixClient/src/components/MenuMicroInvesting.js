import React from 'react'
import { StyleSheet, View, Text, Dimensions, TouchableOpacity } from 'react-native';
import MicroInvestingMenuPigSvg from '../../assets/img/MicroInvestingMenuPigSvg'
import MicroInvestingIconSvg from '../../assets/img/MicroInvestingIconSvg'

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const MenuMicroInvesting = ({microInvestingValue, onPress}) => {
    return(
        <TouchableOpacity onPress={onPress}>
            <View style={styles.objectView}>
                <View style={styles.headerView}>
                    <View style={styles.image}>
                        <MicroInvestingIconSvg/>
                    </View>
                    <Text style={styles.title}>Micro-investimento</Text>
                </View>
                <View style={styles.container}>
                    <View>
                        <MicroInvestingMenuPigSvg/>
                    </View>
                    <View style={styles.microInvestingTextView}>
                        <Text style={styles.microInvestingValue}>{microInvestingValue}</Text>
                        <Text style={styles.microInvestingText}>economizados em micro-investimentos</Text>
                        <Text style={styles.navigationButton}>Ajustar</Text>
                    </View>
                </View>
            </View>
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: 'row'
    },
    microInvestingValue: {
        color: '#64B330',
        fontSize: 18,
        fontWeight: 'bold'
    },
    microInvestingText: {
        fontSize: 10,
        color: '#818181'
    },
    microInvestingTextView: {
        justifyContent: 'center',
        textAlign: 'justify',
    },
    objectView: {
        backgroundColor: '#FFFFFF',
        borderRadius: 10,
        borderWidth: 1,
        borderColor: '#DCDCDC',
        marginHorizontal: 15
    },
    navigationButton : {
        color: '#8F2BFA',
        fontWeight: 'bold',
        marginTop: height * 0.05,
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

export default MenuMicroInvesting;