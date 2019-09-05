import {applyMiddleware, combineReducers, compose, createStore} from 'redux';
import { persistStore, persistReducer } from 'redux-persist';
import thunk from "redux-thunk";
import storage from 'redux-persist/lib/storage';

import autoMergeLevel2 from 'redux-persist/lib/stateReconciler/autoMergeLevel2';

import authReducer from './reducers/authReducer';
import uiLoadingReducer from "./reducers/uiLoadingReducer";
import profileReducer from "./reducers/profileReducer";
import collectionsReducer from "./reducers/collectionsReducer";
import cardReducer from "./reducers/cardReducer";

const rootReducer = combineReducers({
    auth: authReducer,
    uiLoading: uiLoadingReducer,
    profile: profileReducer,
    collections: collectionsReducer,
    card: cardReducer
});

const persistConfig = {
    key: 'root',
    storage: storage,
    whitelist: ['auth'],
    //stateReconciler: autoMergeLevel2
};

const pReducer = persistReducer(persistConfig, rootReducer);

export const store =  createStore(pReducer, compose(applyMiddleware(thunk)));

export const persistor = persistStore(store);