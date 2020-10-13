import React from 'react'
import {Text, View, StyleSheet, Dimensions, Button} from 'react-native'
import { FlatList, TouchableOpacity } from 'react-native-gesture-handler';
import GoalsIconSvg from '../../../assets/img/GoalsIconSvg'
import GoalsDescription from './GoalDescription'
import { navigate } from '../../navigationRef';

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

const stub = () => {
    return -1
}

const GoalsComponent = ({goals}) => {
    
    return (
        <View>
            <View style={styles.headerView}>
                <View style={styles.image}>
                    <GoalsIconSvg/>
                </View>
                <View style={{flex: 1}}>
                    <Text style={styles.title}>Metas</Text>
                    <FlatList
                    data={goals.goalsInfo}
                    horizontal
                    keyExtractor={(key) => { return (key.name + key.totalValue + key.actualValue)}}
                    showsHorizontalScrollIndicator={false}
                    renderItem={( {item} ) => {
                        return (
                        <View style={{marginRight: width * 0.05}}>
                            <GoalsDescription goal={item}/>
                        </View>
                        )
                    }}
                    />
                    <TouchableOpacity onPress={() => navigate('CreateGoal')}>
                        <View style={styles.createGoalButton}>
                            <Text style={styles.goalButtonText}>Criar nova meta</Text>
                        </View>
                    </TouchableOpacity>
                </View>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    headerView : {
        flexDirection: 'row',
        marginTop: '5%',
        marginLeft: '3%',
        marginBottom: '7%',
    },
    image: {
        width: width * 0.05,
        height: height * 0.02, 
        marginRight: '3%',
        marginTop: '1%',
        paddingLeft: width * 0.01
    },
    title : {
        color: '#434343',
        fontStyle: 'italic',
        marginBottom: height * 0.02
    },
    createGoalButton: {
        backgroundColor: '#8516FA',
        overflow: 'hidden',
        borderRadius: 10,
        marginHorizontal: width * 0.2,
        width: width * 0.4,
        height: height * 0.05,
        justifyContent: 'center',
        marginTop: height * 0.04
    },
    goalButtonText: {
        color: 'white',
        textAlign: 'center',
        fontSize: width * 0.04
    }
});

export default GoalsComponent;