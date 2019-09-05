import React, { Component } from 'react';
import {StyleSheet, Image, View, ActivityIndicator, ImageBackground} from "react-native";
import {Button, Text, Root} from 'native-base';
import { StatusBar } from 'react-native';
import logo from "../assets/images/logo.png";
import texture from "../assets/images/texture1.jpg";

import FIcon from 'react-native-vector-icons/FontAwesome';
import MCIcon from 'react-native-vector-icons/MaterialCommunityIcons';

import { connect } from "react-redux";
import { signUpAnonymous } from "../store/actions/authActions";

import DismissKeyboard from 'dismissKeyboard';
import { loadLocaleFile, getAllAchievements } from '../store/actions/profileActions';
import { checkIfAccountExists, loginWithFacebook } from '../store/actions/authActions';

import { strings } from './i18n';
import AppStyles from './AppStyles';

class WelcomeScreen extends Component {

    render() {

        var playButtonObject = <ActivityIndicator size="large" color={AppStyles.primaryColor} style={{ marginTop: 25 }} />;
        var loginButtonObject = null;

        if (this.props.isLocaleFileLoading == false && this.props.isLocaleFileLoaded == true) {
            playButtonObject = (
                <Button transparent iconLeft disabled={this.props.isLoading} style={styles.playButton} onPress={this.getTokenHandler} >
                    <FIcon name='play-circle' style={{ fontSize: 40, color: AppStyles.primaryColor, marginRight: 0 }} />
                    <Text uppercase={false} style={{  marginLeft:0, fontSize: 40, fontWeight: 'bold', color: AppStyles.primaryColor }}>{strings('app.play')}</Text>
                </Button>
            );
            if(this.props.fbValidated == true) {
                loginButtonObject = (
                    <Text style={{
                        alignSelf:"center",
                        fontSize:25,
                        fontWeight: 'bold',
                        color: AppStyles.primaryColor,
                        marginBottom: 30
                    }}>{this.props.fullname}</Text>
                );
            } else {
                loginButtonObject = (
                    <Button block transparent disabled={this.props.isLoading} style={styles.loginButton}
                            onPress={this.authFacebookHandler}>
                        <FIcon name='facebook-official' size={25} color="#3b5998"/>
                        <Text uppercase={false} style={{
                            fontWeight: 'bold',
                            color: AppStyles.primaryColor
                        }}>{strings('app.loginWithFacebook')}</Text>
                    </Button>
                );
            }
        }
        if (this.props.isLocaleFileLoading == false && this.props.isLocaleFileLoaded == false) {
            playButtonObject = (
                <Button transparent iconLeft style={styles.playButton} onPress={this.retryLocales} >
                    <MCIcon name='reload' style={{ fontSize: 40, color: AppStyles.primaryColor, marginRight:0 }} />
                    <Text uppercase={false} style={{ marginLeft:0, fontSize: 40, fontWeight: 'bold', color: AppStyles.primaryColor }}>{strings('app.retry')}</Text>
                </Button>
            );
        }

        return (
            <Root>
                <StatusBar hidden={true} />
                <ImageBackground source={texture} style={{ width: "100%", flex: 1 }}>
                    <View style={styles.container}>
                        <Image source={logo} style={styles.logo} />
                        {playButtonObject}
                        <View style={{ height: 3, width: 280, backgroundColor: AppStyles.primaryColor, marginTop: 30 }}></View>
                        <ActivityIndicator animating={this.props.isLoading} size="large" color={AppStyles.primaryColor} />
                    </View>
                    {loginButtonObject}
                </ImageBackground>
            </Root>
        )
    };

    componentWillMount() {
        this.props.loadLocaleFile();
        this.props.getAllAchievements();
    }

    getTokenHandler = () => {
        if(this.props.token != null && this.props.accountId != null ) {
            this.props.checkIfAccountExists();
        } else {
            this.props.signUpAnonymous();
        }
    };

    authFacebookHandler = () => {
        DismissKeyboard();
        this.props.onTryAuthFacebook();
    };

    retryLocales = () => {
        this.props.loadLocaleFile();
    };
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'flex-start',
        alignContent: 'center',
        alignItems: 'center',
        paddingTop:0,
        marginTop:0
    },
    logo: {
        marginTop:50,
        width:300,
        height:300,
        resizeMode:"cover"
    },
    playButton: {
        height: 60,
        alignSelf: 'center',
        marginTop:20
    },
    backgroundImage: {
        backgroundColor:'#FEF6DF',
        width: "100%",
        flex: 1
    },
    loginButton: {
        width: '80%',
        marginBottom: 20,
        alignSelf: 'center'
    }
});

const mapStateToProps = state => {
    return {
        isLoading: state.uiLoading.signUpAnonymous,
        isLocaleFileLoading: state.uiLoading.loadLocaleFile,
        isLocaleFileLoaded: state.profile.isLocaleFileLoaded,
        token: state.auth.token,
        accountId: state.auth.accountId,
        fbValidated: state.auth.fbValidated,
        fullname: state.auth.fullname,
    };
};

const mapDispatchToProps = dispatch => {
    return {
		getAllAchievements: () => dispatch(getAllAchievements()),
        signUpAnonymous: () => dispatch(signUpAnonymous()),
        loadLocaleFile: () => dispatch(loadLocaleFile()),
        checkIfAccountExists: () => dispatch(checkIfAccountExists()),
        onTryAuthFacebook: () => dispatch(loginWithFacebook())
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(WelcomeScreen);