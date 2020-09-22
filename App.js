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

const switchNavigator = createSwitchNavigator({
  loginFlow: createStackNavigator({
    Login: LoginScreen,
    Signup: SignUpScreen,
  }),
  mainFlow: createStackNavigator({
    Menu: MenuScreen,
    GoalsFlow: createBottomTabNavigator({
      Goals: GoalsScreen,
      MicroInvesting: MicroInvestingScreen,
    }),
    Stores: StoresScreen,
    StoreDetail: StoreDetailScreen,
  }),
});

export default createAppContainer(switchNavigator);