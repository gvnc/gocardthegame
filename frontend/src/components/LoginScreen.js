import React, { Component } from 'react';
import { StyleSheet, ImageBackground, KeyboardAvoidingView, ActivityIndicator,Alert } from "react-native";
import {Item, Input, Button, Text, H1, Root} from 'native-base';
import FIcon from 'react-native-vector-icons/FontAwesome';

import backgroundImage from "../assets/images/texture2.jpg";

import { connect } from "react-redux";
import {login, loginWithFacebook} from "../store/actions/authActions";

import { strings } from './i18n';
import AppStyles from './AppStyles';
import NavigationService from '../store/actions/NavigationService';

import DismissKeyboard from 'dismissKeyboard';

class LoginScreen extends Component {

    state = {
        username: "",
        password: ""
    }

    render() {
        return (
            <Root>
                <ImageBackground source={backgroundImage} style={styles.backgroundImage}>
                    <KeyboardAvoidingView style={styles.container} behavior="padding">
                        <H1 style={{ marginBottom: 30 }}>{strings('app.signinTitle')}</H1>
                        <Item style={styles.inputText}>
                            <Input placeholder={strings('app.username')} value={this.state.username}
                                   onChangeText={val => { this.setLocalState('username',val)}} />
                        </Item>
                        <Item style={styles.inputText}>
                            <Input placeholder={strings('app.password')} value={this.state.password}
                                   onChangeText={val => { this.setLocalState('password',val)}} secureTextEntry={true} />
                        </Item>
                        <Button block disabled={this.props.isLoading} style={styles.loginButton} onPress={this.authHandler} >
                            <Text uppercase={false} style={{ color: '#ffffff' }}>{strings('app.login')}</Text>
                        </Button>
                        <Text style={{ marginTop: 50 }}>{strings('app.signUpMessage')}</Text>
                        <Button block bordered disabled={this.props.isLoading} style={styles.loginButton} onPress={this.navigateToSignup} >
                            <FIcon name='user-plus' size={25} color="#009900"/>
                            <Text uppercase={false} >{strings('app.signUp')}</Text>
                        </Button>
                        <Button block bordered disabled={this.props.isLoading} style={styles.loginButton} onPress={this.authFacebookHandler} >
                            <FIcon name='facebook-official' size={25} color="#3b5998"/>
                            <Text uppercase={false} >{strings('app.loginWithFacebook')}</Text>
                        </Button>
                        <ActivityIndicator animating={this.props.isLoading} size="large" color={AppStyles.primaryColor} />
                    </KeyboardAvoidingView>
                </ImageBackground>
            </Root>
        )
    };

    setLocalState(variable, newValue){
        this.setState({
            ...this.state,
            [variable]: newValue
        });
    }

    authHandler = () => {
        DismissKeyboard();
        this.props.onTryAuth(this.state.username, this.state.password);
    };

    authFacebookHandler = () => {
        DismissKeyboard();
        this.props.onTryAuthFacebook();

    };

    navigateToSignup = () => {
        NavigationService.navigate("Signup");
    };

    navigateToWelcomeScreen = () => {
        NavigationService.navigate("Welcome");
    };
}

const styles = StyleSheet.create({
    inputText: {
        width: '80%',
        marginBottom: 5
    },
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    loginButton: {
        width: '80%',
        marginTop: 15,
        alignSelf: 'center'
    },
    backgroundImage: {
        width: "100%",
        flex: 1
    }
});


const mapStateToProps = state => {
    return {
        isLoading: state.uiLoading.login
    };
};


const mapDispatchToProps = dispatch => {
    return {
        onTryAuth: (username, password) => dispatch(login(username, password)),
        onTryAuthFacebook: () => dispatch(loginWithFacebook()),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginScreen);