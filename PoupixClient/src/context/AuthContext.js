import {AsyncStorage} from 'react-native';
import createDataContext from './createDataContext';
import { navigate } from '../navigationRef';
import poupixApi from '../api/poupixApi'

const authReducer = (state, action) => {
    switch(action.type){
        case 'add_error':
            return {...state, errorMessage: action.payload};
        case 'signin':
            return { errorMessage: '', token: action.payload};
        case 'signout':
            return {token: null, errorMessage: ''};
        case 'clear_error_message':
            return { ...state, errorMessage: '' };
        default:
            return state;
    }
}

const clearErrorMessage = dispatch => () => {
    dispatch({ type: 'clear_error_message' });
};


const tryLocalSignIn = (dispatch) => {
    return async () => {
        const token = await AsyncStorage.getItem('token')
        if(token){
            dispatch({ type:'signin', payload: token })
            navigate('mainFlow')
        }else{
            navigate('loginFlow')
        }
    }
}

const signIn = (dispatch) => {
    return async ({ cpf, password }) => {
        try{
            const response = await poupixApi.post('/login', {username: cpf, password})
            await AsyncStorage.setItem('token', response.data.access_token)
            dispatch({type: 'signin', payload: response.data.token});
            navigate('mainFlow')
        }catch(err){
            console.log(err)
            dispatch({ type: 'add_error', payload: 'Something went wrong with sign in'})
        }
    };
}

const signOut = (dispatch) => {
    return async () => {
        await AsyncStorage.removeItem('token');
        dispatch({ type: 'signout' });
        navigate('loginFlow');
    };
}

export const {Provider, Context} = createDataContext(
    authReducer,
    {signIn, signOut, tryLocalSignIn, clearErrorMessage},
    { token: null, errorMessage: '' }
)