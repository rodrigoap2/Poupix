import { createAppContainer, createSwitchNavigator } from 'react-navigation';
import { createStackNavigator } from 'react-navigation-stack';
import LoginScreen from './src/screens/LoginScreen';
import SignUpScreen from './src/screens/SignUpScreen';
import MenuScreen from './src/screens/MenuScreen';
import GoalDetailScreen from './src/screens/GoalDetailScreen';
import GoalsScreen from './src/screens/GoalsScreen';
import StoresScreen from './src/screens/StoresScreen';
import StoreDetailScreen from './src/screens/StoreDetailScreen';

const switchNavigator = createSwitchNavigator({
  loginFlow: createStackNavigator({
    Login: LoginScreen,
    Signup: SignUpScreen,
  }),
  mainFlow: createStackNavigator({
    Menu: MenuScreen,
    Goals: GoalsScreen,
    GoalDetail: GoalDetailScreen,
    Stores: StoresScreen,
    StoreDetail: StoreDetailScreen,
  }),
});

export default createAppContainer(switchNavigator);