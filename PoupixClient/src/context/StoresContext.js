import createDataContext from './createDataContext';
import poupixApi from '../api/poupixApi'
import * as Location from 'expo-location';
import { getDistance } from 'geolib';

const storesReducer = (state, action) => {
    switch(action.type){
        case 'get_stores':
            return {...state, stores: action.payload};
        case 'get_one_store':
            return {...state, detailedStore: action.payload};
        default:
            return state;
    }
}

const getStores = (dispatch) => {
    return async () => {
        try{
            let location = await Location.getCurrentPositionAsync({});
            console.log(location)
            //console.log(`/stores/${location.coords.latitude}/${location.coords.longitude}`)
            const response = await poupixApi.get(`/stores/nearby/-8.049829/-34.895319`)
            const stores2 = response.data.stores
            stores2.map((element) => {
                element.distance = getDistance(
                    { latitude: location.coords.latitude, longitude: location.coords.longitude },
                    { latitude: element.coordinates.lat, longitude: element.coordinates.lon}
                )
                if(element.distance < 1000) {
                    element.distance += 'm'
                }else{
                    element.distance = (element.distance/1000).toFixed(1) + 'km'
                }
                element.cashback = (element.cashback*100).toFixed(1) + '%'
            })
            dispatch({type: 'get_stores', payload: stores2});
        }catch(err){
            console.log('Erro na requisição de lojas')
        }
    };
}

const getOneStore = (dispatch) => {
    return async (id) => {
        try{
            console.log(id)
            const response = await poupixApi.get('/stores/' + id)
            console.log(response.data)
            dispatch({type: 'get_one_store', payload: response.data});
        }catch(err){
            console.log('Erro na requisição de lojas')
        }
    };
}

export const {Provider, Context} = createDataContext(
    storesReducer,
    {getStores, getOneStore},
    { stores: [], nome: 'Rodrigo', detailedStore: {}}
)