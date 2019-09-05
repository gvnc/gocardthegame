import { AUTH_SET_TOKEN, SIGNUP, SET_OPENING_INTRO, SET_HINT_DISPLAYED } from './actionTypes';
import { uiStartLoading, uiStopLoading } from "./uiLoadingActions";
import NavigationService from './NavigationService';
import apiConfig from './apiConfig';
import { strings } from '../../components/i18n';
import { Toast } from 'native-base';
import { setNewAchievementState, accomplishAchievementById} from './profileActions';

export const loginWithFacebook = () => {

    return (dispatch, getState) => {
        
        let accountId = getState().auth.accountId;
        if(accountId === undefined || accountId === null){
            accountId = "";
        }

        dispatch(uiStartLoading("login"));

        const logInWithFacebookAsync = async function loginFb() {
            let facebookResult="";
            let responseFacebookJson="";
            
            const { type, token } = await Expo.Facebook.logInWithReadPermissionsAsync(apiConfig.facebookAppId, {
                permissions: ['public_profile', 'email'],
            });

            if (type === 'success') {

                let url = apiConfig.url + "/users/loginWithFacebook";
                let loginStr = "accessToken=" + token + "&accountId=" + accountId;

                fetch(url, {
                    method: "POST",
                    body: loginStr,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    }
                })
                    .catch(err => {
                        alert("error:" + err);
                        dispatch(uiStopLoading("login"));
                    })
                    .then(response => response.json())
                    .then(
                        response => {
                            if (response.loginResult !== undefined) {
                                if (response.loginResult === true) {
                                    dispatch(authSetToken(response));
                                    NavigationService.navigate("MainTab");

                                    var facebookLoginAchievementId = 7;
                                    dispatch(accomplishAchievementById(facebookLoginAchievementId));
                                } else {
                                    Toast.show({
                                        text: strings("app." + response.loginMessage),
                                        duration: 5000,
                                        position: "bottom",
                                        style: {backgroundColor: '#d9534f'}
                                    })
                                }
                            }
                            dispatch(uiStopLoading("login"));
                        }
                    )
                    .catch(function (error) {
                        return error;
                    });

            } else {
                //Alert.alert('NOT SUCCESS !');
                dispatch(uiStopLoading("login"));
            }
            return facebookResult;
        }
        logInWithFacebookAsync();
    };
}

export const authSetToken = (response) => {
    return {
        type: AUTH_SET_TOKEN,
        token: response.token,
        user: response.user
    };
};

export const signUpAnonymous = () => {

    return dispatch => {

        dispatch(uiStartLoading("signUpAnonymous"));

        let url = apiConfig.url + "/users/signupAnonymous";
        fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .catch(err => {
                alert("error:" + err);
                dispatch(uiStopLoading("signUpAnonymous"));
            })
            .then(response => response.json())
            .then(
                response => {
                    if (response.loginResult !== undefined) {
                        if (response.loginResult === true) {
                            dispatch(authSetToken(response));
                            NavigationService.navigate("MainTab");
                        } else {
                            alert(response.loginMessage);
                        }
                    }
                    dispatch(uiStopLoading("signUpAnonymous"));
                }
            )
    };
}

export const signup = (fullname, email, password) => {

    return (dispatch, getState) => {

        const accountId = getState().auth.accountId;

        dispatch(uiStartLoading("signup"));

        let url = apiConfig.url + "/users/signup";
        let signupStr = "fullname=" + fullname + "&email=" + email + "&password=" + password + "&signInType=REGULAR";

        if(accountId !== undefined && accountId !== null ){
            signupStr = "accountId=" + accountId + "&" + signupStr;
            url = apiConfig.url + "/users/signupWithExistingAccount";
        }

        fetch(url, {
            method: "POST",
            body: signupStr,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .catch(err => {
                alert("error:" + err);
                dispatch(uiStopLoading("signup"));
            })
            .then(response => response.json())
            .then(
                response => {
                    if (response.signupResult !== undefined) {
                        if (response.signupResult === true) {
                            var signupAchievementId = 6;
                            dispatch(setNewAchievementState(signupAchievementId));
                            dispatch(login(email, password));
                        } else {
                            Toast.show({
                                text: strings("app." + response.signupMessage),
                                duration: 5000,
                                position: "bottom",
                                style: {backgroundColor:'#d9534f'}
                              })
                        }
                        dispatch(setSignupValues(response));
                    }
                    dispatch(uiStopLoading("signup"));
                }
            )
    };
}

export const setSignupValues = (response) => {
    return {
        type: SIGNUP,
        signupResult: response.signupResult,
        signupMessage: response.signupMessage
    };
};

export const checkIfAccountExists = () => {

    return (dispatch, getState) => {

        dispatch(uiStartLoading("signUpAnonymous"));

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        let url = apiConfig.url + "/users/checkIfAccountExists";
        let authHeader = "Bearer " + token;

        let bodyStr = "accountId=" + accountId;
        fetch(url, {
            method: "POST",
            body: bodyStr,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': authHeader
            }
        })
            .catch(err => {
                alert("error:" + err);
                dispatch(uiStopLoading("signUpAnonymous"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(uiStopLoading("signUpAnonymous"));
                    if (response.loginResult === false) {
                        dispatch(signUpAnonymous());
                    } else {
                        NavigationService.navigate("MainTab");
                    }
                }
            )
    };
}

export const setOpeningIntro = (newValue) => {
    return {
        type: SET_OPENING_INTRO,
        value: newValue
    };
};

export const setHintDisplayed = (newValue) => {
    return {
        type: SET_HINT_DISPLAYED,
        value: newValue
    };
};
