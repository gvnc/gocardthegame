import { AUTH_SET_TOKEN, AUTH_REMOVE_TOKEN, SIGNUP, SET_OPENING_INTRO, SET_HINT_DISPLAYED } from "../actions/actionTypes";

const initialState = {
    token: null,
    email: null,
    fullname: null,
    accountId: null,
    fbValidated: false,
    signupMessage: "",
    displayedOpeningIntro: false,
    collectionHintDisplayed: false
};

const reducer = (state = initialState, action) => {

    switch (action.type) {
        case AUTH_SET_TOKEN:
            return {
                ...state,
                token: action.token,
                email: action.user.email,
                fullname: action.user.fullname,
                accountId: action.user.accountId,
                fbValidated: action.user.fbValidated
            };
        case AUTH_REMOVE_TOKEN:
            return {
                ...state,
                token: null,
                email: null,
                accountId: null
            };
        case SIGNUP:
            return {
                ...state,
                signupResult: action.signupResult,
                signupMessage: action.signupMessage
            };
        case SET_OPENING_INTRO:
            return {
                ...state,
                displayedOpeningIntro: action.value
            };
        case SET_HINT_DISPLAYED:
            return {
                ...state,
                collectionHintDisplayed: action.value
            };
        default:
            return state;
    }
};

export default reducer;  