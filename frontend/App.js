import React from 'react';
import {Provider} from 'react-redux';
import {Text} from 'react-native';
import Main from './Main';

import { StyleProvider } from 'native-base';
import getTheme from './native-base-theme/components';
import appColors from './native-base-theme/variables/appColors';

import { PersistGate } from 'redux-persist/lib/integration/react';
import { store, persistor } from './src/store/configureReduxPersistStore';

const RNRedux = () => (
    <Provider store={store}>
        <PersistGate loading={<Text></Text>} persistor={persistor}>
            <StyleProvider style={getTheme(appColors)}>
                <Main />
            </StyleProvider>
        </PersistGate>
    </Provider>
);

export default RNRedux;