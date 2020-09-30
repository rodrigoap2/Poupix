import createDataContext from './createDataContext';
import poupixApi from '../api/poupixApi'

const goalsReducer = (state2, action) => {
    switch(action.type){
        case 'get_goals':
            return {...state, goals: action.payload};
        default:
            return state;
    }
}

const getGoals = (dispatch) => {
    return async () => {
        try{
            //const response = await poupixApi.get('/stores')
            const goals = {
                'roundup': {
                    'lastMonth': 30,
                    'total': 300,
                    'valuePerTransaction': 1
                },
                'goals': {
                    'thisMonthPercentage':0.7,
                    'thisMonthTotalValue': 872.23,
                    'goalsInfo': [
                        {
                            'name': 'Casa',
                            'totalValue': 400119.99,
                            'actualValue': 200059.99,
                            'actualMonth': 23,
                            'totalMonths': 100
                        },
                        {
                            'name': 'Computador',
                            'totalValue': 4199.99,
                            'actualValue': 3295.99,
                            'actualMonth': 23,
                            'totalMonths': 100
                        },
                    ]
                },
                'account': {
                    'balance': 202119.98,
                    'lastYear': 10709.50,
                    'revenue': 251.79,
                    'revenueIndex': '100% do CDI',
                    'scheduled': 20.40
                }
            }
            dispatch({type: 'get_goals', payload: goals});
        }catch(err){
            console.log('Erro na requisição das metas')
        }
    };
}

export const {Provider, Context} = createDataContext(
    goalsReducer,
    {getGoals},
    { goals: {}}
)