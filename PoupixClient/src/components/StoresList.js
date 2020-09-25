import React from 'react'
import { StyleSheet, View } from 'react-native';
import { FlatList } from 'react-native-gesture-handler'
import StoreDescription from './StoreDescription'

const StoresList = ({stores, navigation}) => {
    return (
    <View style={{flex: 1}}>
            <FlatList
                data={stores}
                style={{ marginBottom: 5}}
                keyExtractor={(result) => result.id}
                renderItem={( item ) => {
                    return (
                    <StoreDescription
                    store={item.item}
                    navigation = {navigation}
                    />
                    );
                }}
            />
        </View>
    );
}

const styles = StyleSheet.create({

});

export default StoresList;