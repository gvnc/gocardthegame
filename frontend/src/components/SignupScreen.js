import React, { Component } from 'react';
import { StyleSheet, ImageBackground, KeyboardAvoidingView, ActivityIndicator } from "react-native";
import { Item, Input, Button, Text, H1, Root } from 'native-base';
import FIcon from 'react-native-vector-icons/FontAwesome';

import backgroundImage from "../assets/images/texture2.jpg";

import { connect } from "react-redux";
import { signup, loginWithFacebook } from "../store/actions/authActions";

import { strings } from './i18n';
import AppStyles from './AppStyles';

import NavigationService from '../store/actions/NavigationService';
import DismissKeyboard from 'dismissKeyboard';

class SignupScreen extends Component {

    state = {
        fullname: "",
        username: "",
        password: "",
        signInType: "REGULAR"
    }

    render() {

        return (
            <Root>
                <ImageBackground source={backgroundImage} style={styles.backgroundImage}>
                    <KeyboardAvoidingView style={styles.container} behavior="padding">
                        <H1 style={{ marginBottom: 30 }}>{strings('app.signupTitle')}</H1>
                        <Item style={styles.inputText}>
                            <Input placeholder={strings('app.fullname')} value={this.state.fullname}
                                   onChangeText={val => { this.setLocalState('fullname', val) }} />
                        </Item>
                        <Item style={styles.inputText}>
                            <Input placeholder={strings('app.username')} value={this.state.username}
                                   onChangeText={val => { this.setLocalState('username', val) }} />
                        </Item>
                        <Item style={styles.inputText}>
                            <Input placeholder={strings('app.password')} value={this.state.password}
                                   onChangeText={val => { this.setLocalState('password', val) }}  secureTextEntry={true} />
                        </Item>
                        <Button block disabled={this.props.isLoading} style={styles.loginButton} onPress={this.signupHandler} >
                            <Text uppercase={false} style={{ color: '#ffffff' }}>{strings('app.signupTitle')}</Text>
                        </Button>
                        <Button transparent disabled={this.props.isLoading} style={styles.loginButton} onPress={this.navigateToLogin} >
                            <Text uppercase={false} >{strings('app.alreadyHaveAccount')}</Text>
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

    signupHandler = () => {
        DismissKeyboard();
        this.props.onTrySignup(this.state.fullname, this.state.username, this.state.password);
    };

    authFacebookHandler = () => {
        DismissKeyboard();
        this.props.onTryAuthFacebook();

    };

    navigateToLogin = () => {
        NavigationService.navigate("Login");
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
        isLoading: state.uiLoading.signup
    };
};


const mapDispatchToProps = dispatch => {
    return {
        onTrySignup: (fullname, username, password) => dispatch(signup(fullname, username, password)),
        onTryAuthFacebook: () => dispatch(loginWithFacebook())
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(SignupScreen);