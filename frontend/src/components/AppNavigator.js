import React from 'react';

import LoginScreen from './LoginScreen';
import TabCollectionScreen from './collections/TabCollectionScreen';
import TabCardScreen from './cards/TabCardScreen';
import TabProfileScreen from './profile/TabProfileScreen';
import CollectionView from './collections/CollectionView';

import { createMaterialBottomTabNavigator } from 'react-navigation-material-bottom-tabs';
import { createStackNavigator } from 'react-navigation';

import FIcon from 'react-native-vector-icons/FontAwesome';
import MIcon from 'react-native-vector-icons/MaterialIcons';
import MCIcon from 'react-native-vector-icons/MaterialCommunityIcons';
import WelcomScreen from './WelcomScreen';

import AppStyles from "./AppStyles";

import { store } from '../store/configureReduxPersistStore';

import { getCardsLeft } from '../store/actions/cardAction';

import { strings } from './i18n';
import SignupScreen from './SignupScreen';

const oneDayInMillis = 24 * 60 * 60 * 1000;

const CollectionStack = createStackNavigator(
    {
        CollectionListView: TabCollectionScreen,
        CollectionView: CollectionView,
    },
    {
        initialRouteName: 'CollectionListView',
        headerMode: 'none',
        navigationOptions: {
            headerVisible: false,
        }
    }
);

const TabStack =
    createMaterialBottomTabNavigator({
        CollectionTab: {
            screen: CollectionStack,
            navigationOptions: () => ({
                tabBarLabel: strings("tabBar.collections"),
                tabBarIcon:  ({tintColor}) => <MIcon name='collections' size={25} color={tintColor}/>
            })
        },
        CardTab: {
            screen: TabCardScreen,
            navigationOptions:  () => ({
                tabBarLabel: strings("tabBar.cards"),
                tabBarIcon:  ({tintColor}) => <MCIcon name='cards' size={25} color={tintColor}/>,
                tabBarOnPress: ({navigation, defaultHandler}) => {
                    var cardsLeft = store.getState().card.cardsLeft;
                    if(cardsLeft == null){
                        store.dispatch(getCardsLeft());
                    } else {
                        var lastRefreshDateTimestamp = cardsLeft.lastDailyCardsRefreshDate;
                        var splitDate = JSON.stringify(lastRefreshDateTimestamp).replace("\"", "").split(".");
                        var lastRefreshDate = new Date(splitDate[0]);
                        var lastRefreshDateInMillis = lastRefreshDate.getTime();
                        var dateNowInMillis = new Date().getTime();
                        if(dateNowInMillis > lastRefreshDateInMillis + oneDayInMillis) {
                            store.dispatch(getCardsLeft());
                        }
                    }
                    defaultHandler();
                }
            })
        },
        ProfileTab: {
            screen: TabProfileScreen,
            navigationOptions:  () => ({
                tabBarLabel: strings("tabBar.profile"),
                tabBarIcon: ({tintColor}) => <FIcon name='user-circle' size={25} color={tintColor}/>
            })
        }
    }, {
            initialRouteName: 'CollectionTab',
            activeTintColor: AppStyles.navigatorActiveTintColor,
            inactiveTintColor: AppStyles.navigatorInactiveTintColor,
            barStyle: {
                backgroundColor: AppStyles.navigatorBackground
            },
            navigationOptions: {
                tabBarOptions: {
                    swipeEnabled: false
                }
            }
        }
    );

export default RootStack = createStackNavigator(
    {
        MainTab: {
            screen: TabStack,
            navigationOptions: {
                gesturesEnabled: false
            }
        },
        Login: LoginScreen,
        Signup: SignupScreen,
        Welcome: WelcomScreen
    },
    {
        initialRouteName: 'Welcome',
        headerMode: 'none',
        navigationOptions: {
            headerVisible: false,
            gesturesEnabled: true
        }
    }
);