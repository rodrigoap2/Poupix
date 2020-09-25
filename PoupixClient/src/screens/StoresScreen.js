import React, {useState, useEffect} from 'react'
import {Text, View, StyleSheet} from 'react-native'
import {Button, SearchBar} from 'react-native-elements'
import MenuStores from '../components/MenuStores'
import StoresList from '../components/StoresList'
import {Context as StoresContext} from '../context/StoresContext'

const StoresScreen = ({navigation}) => {
    const [searchWord, setSearchWord] = useState('')
    const stores = navigation.getParam('stores')
    const [storesFiltered, setStoresFiltered] = useState(stores)

    const transferStores = () => setStoresFiltered(stores)

    useEffect(() => {
        transferStores
    },[])

    const searchFilterFunction = text => {    
        const newData = stores.filter(item => {   
          const itemData = `${item.name.toUpperCase()}   
          ${item.type.toUpperCase()}`;
          
           const textData = text.toUpperCase();
            
           return itemData.indexOf(textData) > -1;    
        });
            setStoresFiltered(newData)
            setSearchWord(text)
    };

    return(
        <View style={styles.container}>
            <SearchBar
                    placeholder=""
                    onChangeText={(text) => searchFilterFunction(text)}
                    value={searchWord}
                    platform='ios'
                    containerStyle={{backgroundColor: '#FAFAFA'}}
                    inputContainerStyle={{backgroundColor: '#dae0e6', borderWidth: 2, borderColor:'transparent', borderRadius: 30}}
            />
            <View style={{flexGrow: 1}}>
                <StoresList
                stores={storesFiltered}
                navigation = {navigation}
                />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 48
    },
    container: {
        backgroundColor: '#FAFAFA',
        flexGrow: 1
    }
});

export default StoresScreen;