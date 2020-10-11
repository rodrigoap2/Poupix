import React from 'react';
import { createAppContainer, createSwitchNavigator } from 'react-navigation';
import { createStackNavigator } from 'react-navigation-stack';
import { createBottomTabNavigator } from 'react-navigation-tabs';
import LoginScreen from './src/screens/LoginScreen';
import SignUpScreen from './src/screens/SignUpScreen';
import MenuScreen from './src/screens/MenuScreen';
import GoalsScreen from './src/screens/GoalsScreen';
import StoresScreen from './src/screens/StoresScreen';
import StoreDetailScreen from './src/screens/StoreDetailScreen';
import MicroInvestingScreen from './src/screens/MicroInvestingScreen';
import { setNavigator } from './src/navigationRef'
import { Provider as AuthProvider } from './src/context/AuthContext';
import { Provider as StoresProvider } from './src/context/StoresContext';

const switchNavigator = createSwitchNavigator({
  loginFlow: createStackNavigator({
    Login: LoginScreen,
    Signup: SignUpScreen,
  }),
  mainFlow: createStackNavigator(
    {
    Menu: MenuScreen,
    GoalsFlow: createBottomTabNavigator({
      Goals: GoalsScreen,
      MicroInvesting: MicroInvestingScreen,
    }, {
      navigationOptions: () => {
        return{
          header: () => false
        }
      }
    }),
    Lojas: StoresScreen,
    StoreDetail: StoreDetailScreen,
    },
    {
      defaultNavigationOptions: {
        headerTitleAlign: 'center',
        headerStyle:{
          shadowColor: 'transparent',
          shadowRadius: 0
        }
      }
    }),
});

const App = createAppContainer(switchNavigator);

export default () => {
  return(
      <StoresProvider>
        <AuthProvider>
          <App ref={(navigator) => setNavigator(navigator)}/>
        </AuthProvider>
      </StoresProvider>
  );
}
