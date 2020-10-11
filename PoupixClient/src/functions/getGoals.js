import poupixApi from '../api/poupixApi'

const getGoals = () => {
    return async () => {
        try{
            const response = await poupixApi.get('/accounts/general-information').then((res) => res).catch((err) => console.log(err))
            const goals = {
                'roundup': {
                    'lastMonth': 30,
                    'total': 30000.20,
                    'roundup': 1
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
            return response.data
        }catch(err){
            console.log('Erro na requisição das metas aff')
            return -1
        }
    };
}

export default getGoals;