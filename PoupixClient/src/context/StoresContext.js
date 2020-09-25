import createDataContext from './createDataContext';
import poupixApi from '../api/poupixApi'

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
            //const response = await poupixApi.get('/stores')
            const stores = [
                {
                    'name': 'Adidas',
                    'id' : '1',
                    'cashback': '25%',
                    'distance' : '300m',
                    'type': 'Sports',
                    'image' : 'https://imgcentauro-a.akamaihd.net/1300x1300/91580601/camiseta-adidas-core-18-masculina-img.jpg'
                },
                {
                    'name': 'Esposende',
                    'id' : '2',
                    'cashback': '20%',
                    'distance' : '500m',
                    'type' : 'Shoes Store',
                    'image' : 'https://a-static.mlcdn.com.br/1500x1500/sapato-social-masculino-ortopedico-linha-gel-lancamento-preto-fran-shoes/sapatofran/4864690109/e45c9805a3164f7be2d36ae40c37db07.jpg'
                },
                {
                    'name': 'Mc Donalds',
                    'id' : '3',
                    'cashback': '10%',
                    'distance' : '100m',
                    'type' : 'Restaurant',
                    'image' : 'https://img.itdg.com.br/tdg/images/blog/uploads/2020/05/shutterstock_1710468256.jpg'
                },
                {
                    'name': 'Mc Donalds',
                    'id' : '4',
                    'cashback': '5%',
                    'distance' : '100m',
                    'type' : 'Restaurant',
                    'image' : 'https://img.itdg.com.br/tdg/images/blog/uploads/2020/05/shutterstock_1710468256.jpg'
                },
                {
                    'name': 'Mc Donalds',
                    'id' : '5',
                    'cashback': '7%',
                    'distance' : '100m',
                    'type' : 'Restaurant',
                    'image' : 'https://img.itdg.com.br/tdg/images/blog/uploads/2020/05/shutterstock_1710468256.jpg'
                },
                {
                    'name': 'Mc Donalds',
                    'id' : '6',
                    'cashback': '7%',
                    'distance' : '100m',
                    'type' : 'Restaurant',
                    'image' : 'https://img.itdg.com.br/tdg/images/blog/uploads/2020/05/shutterstock_1710468256.jpg'
                },
                {
                    'name': 'AAAA',
                    'id' : '9',
                    'cashback': '7%',
                    'distance' : '100m',
                    'type' : 'Restaurant',
                    'image' : 'https://img.itdg.com.br/tdg/images/blog/uploads/2020/05/shutterstock_1710468256.jpg'
                },
                {
                    'name': 'AAAA',
                    'id' : '8',
                    'cashback': '8%',
                    'distance' : '100m',
                    'type' : 'Restaurant',
                    'image' : 'https://img.itdg.com.br/tdg/images/blog/uploads/2020/05/shutterstock_1710468256.jpg'
                },
            ]
            dispatch({type: 'get_stores', payload: stores});
        }catch(err){
            console.log('Erro na requisição de lojas')
        }
    };
}

const getOneStore = (dispatch) => {
    return async (id) => {
        try{
            //const response = await poupixApi.get('/stores/' + id)
            const store = {
                'name': 'Mc Donalds',
                'description': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
                'cashback': {
                    'monday': 0.07,
                    'tuesday': 0.1,
                    'wednesday': 0.08,
                    'thursday': 0.06,
                    'friday': 0.05,
                    'saturday': 0.03,
                    'sunday': 0.02
                },
                'pictures' : ['https://i.ytimg.com/vi/CMFhfr42QLg/maxresdefault.jpg', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRHUkd-jijdtMAz7LfGGGmTpSIyi5kLY4Y4TQ&usqp=CAU'],
                'address' : 'Av. Gov. Agamenon Magalhães, 990 - Graças, Recife - PE, 52031-330',
                'coordinates' : {
                    'x' : -8.049829,
                    'y' : -34.895319
                }
            }
            console.log(store + 'aaaaaaaaaaaaaa')
            dispatch({type: 'get_one_store', payload: store});
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