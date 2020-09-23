import React from 'react'
import { StyleSheet, View } from 'react-native';
import { FlatList } from 'react-native-gesture-handler'
import SmallStoreDescription from '../components/SmallStoreDescription'

const MenuStores = ({stores}) => {
    return(
        <View>
            <FlatList
                data={stores}
                keyExtractor={(result) => result.nome}
                showsHorizontalScrollIndicator={false}
                renderItem={( item ) => {
                    return (
                    <SmallStoreDescription
                    store={item.item}
                    />
                    );
                }}
            />
        </View>
    )
}

const styles = StyleSheet.create({

});

export default MenuStores;