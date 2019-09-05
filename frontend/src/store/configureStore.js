import { createStore, combineReducers, compose, applyMiddleware } from "redux";
import thunk from "redux-thunk";

import authReducer from './reducers/authReducer';
import uiLoadingReducer from './reducers/uiLoadingReducer';
import profileReducer from './reducers/profileReducer';
import collectionsReducer from './reducers/collectionsReducer';
import cardReducer from './reducers/cardReducer';

const rootReducer = combineReducers({
	auth: authReducer,
	uiLoading: uiLoadingReducer,
	profile: profileReducer,
	collections: collectionsReducer,
	card: cardReducer
});

export default createStore(rootReducer, compose(applyMiddleware(thunk)));