import createDataContext from './createDataContext';
import poupixApi from '../api/poupixApi'

const storesReducer = (state, action) => {
    switch(action.type){
        case 'get_stores':
            return {...state, stores: action.payload};
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
                    'cashback': '25%',
                    'distance' : '300m',
                    'image' : 'https://imgcentauro-a.akamaihd.net/1300x1300/91580601/camiseta-adidas-core-18-masculina-img.jpg'
                },
                {
                    'name': 'Esposende',
                    'cashback': '20%',
                    'distance' : '500m',
                    'image' : 'https://a-static.mlcdn.com.br/1500x1500/sapato-social-masculino-ortopedico-linha-gel-lancamento-preto-fran-shoes/sapatofran/4864690109/e45c9805a3164f7be2d36ae40c37db07.jpg'
                },
                {
                    'name': 'Mc Donalds',
                    'cashback': '10%',
                    'distance' : '100m',
                    'image' : 'https://img.itdg.com.br/tdg/images/blog/uploads/2020/05/shutterstock_1710468256.jpg'
                }
            ]
            dispatch({type: 'get_stores', payload: stores});
        }catch(err){
            console.log('Erro na requisição de lojas')
        }
    };
}

export const {Provider, Context} = createDataContext(
    storesReducer,
    {getStores},
    { stores: [], nome: 'Rodrigo'}
)