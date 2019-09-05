import { GET_GAME_STATS, GET_ALL_ACHIEVEMENTS, SET_ACHIEVEMENT_DONE, LOAD_LOCALE_FILE, SET_NEW_ACHIEVEMENT_STATE, SET_ACHIEVEMENT_POPUP } from "../actions/actionTypes";
import { uiStartLoading, uiStopLoading } from "./uiLoadingActions";
import { getCardsLeft } from "./cardAction";
import { Toast } from 'native-base';
import apiConfig from './apiConfig';

import { getLocale, addLocale, strings } from '../../components/i18n';

export const loadLocaleFile = () => {

    return (dispatch) => {

        dispatch(uiStartLoading("loadLocaleFile"));
        let currentLocale = getLocale();
        let url = apiConfig.url + "/locales/" + currentLocale.substring(0,2) + ".json";

        fetch(url, {
            method: "GET",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .catch(err => {
                Toast.show({
                    text: strings("app.networkIssue"),
                    duration: 2000,
                    position: "bottom",
                    style: { backgroundColor: '#d9534f' }
                });
                dispatch(setLocaleFile(false));
                dispatch(uiStopLoading("loadLocaleFile"));
            })
            .then(response => response.json())
            .then(
                response => {
                    addLocale(currentLocale, response);
                    dispatch(setLocaleFile(true));
                    dispatch(uiStopLoading("loadLocaleFile"));
                }
            )
    };
};

export const setLocaleFile = (isLocaleFileLoaded) => {
    return {
        type: LOAD_LOCALE_FILE,
        isLocaleFileLoaded: isLocaleFileLoaded
    };
};

export const getGameStats = () => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(uiStartLoading("gameStats"));

        let url = apiConfig.url + "/profile/getGameStatsByAccountId";
        let bodyStr = "accountId=" + accountId;
        let authHeader = "Bearer " + token;
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
                dispatch(uiStopLoading("gameStats"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setGameStats(response));
                    dispatch(uiStopLoading("gameStats"));
                }
            )
    };

};

export const setGameStats = (gameStats) => {
    return {
        type: GET_GAME_STATS,
        gameStats: gameStats
    };
};

export const getAllAchievements = () => {

    return dispatch => {

        dispatch(uiStartLoading("allAchievements"));

        let url = apiConfig.url + "/profile/getAllAchievements";
        fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .catch(err => {
                dispatch(uiStopLoading("allAchievements"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setAllAchievements(response));
                    dispatch(uiStopLoading("allAchievements"));
                }
            )
    };

};

export const setAllAchievements = (allAchievements) => {
    return {
        type: GET_ALL_ACHIEVEMENTS,
        allAchievements: allAchievements
    };
};

export const isAchievementDone = (achievementId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(uiStartLoading("isAchievementDone"));
        dispatch(setAchievementDone(null));

        let url = apiConfig.url + "/profile/isAchievementDone";
        let authHeader = "Bearer " + token;
        let bodyStr = "accountId=" + accountId + "&achievementId=" + achievementId;
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
                dispatch(uiStopLoading("isAchievementDone"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setAchievementDone(response));
                    dispatch(uiStopLoading("isAchievementDone"));
                }
            )
    };
};

export const setAchievementDone = (accountAchievement) => {
    return {
        type: SET_ACHIEVEMENT_DONE,
        accountAchievement: accountAchievement
    };
};

export const accomplishAchievementById = (achievementId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(setAchievementDone(null));

        let url = apiConfig.url + "/profile/accomplishAchievementById";
        let authHeader = "Bearer " + token;
        let bodyStr = "accountId=" + accountId + "&achievementId=" + achievementId;
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
            })
            .then(response => response.json())
            .then(
                response => {
                    if(response.error !== undefined){
                        //console.log(JSON.stringify(response));
                    } else {
                        dispatch(setAchievementDone(response));
                        dispatch(setAchievementPopupVisible(true));
                        dispatch(getGameStats());
                        dispatch(getCardsLeft());
                        dispatch(setNewAchievementState(0));
                    }
                }
            )
    };
};

export const setNewAchievementState = (achievementId) => {
    return {
        type: SET_NEW_ACHIEVEMENT_STATE,
        newAchievementId: achievementId
    };
};

export const setAchievementPopupVisible = (isVisible) => {
    return {
        type: SET_ACHIEVEMENT_POPUP,
        openAchievementPopup: isVisible
    };
};

export const checkIfAnyAchievementsAccomplished = () => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(setAchievementDone(null));

        let url = apiConfig.url + "/profile/checkIfAnyAchievementsAccomplished";
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
            })
            .then(
                response => {
                    return response.json();
                }
            )
            .then(
                response => {
                    if (response != null && response.id != null) {
                        dispatch(setAchievementDone(response));
                        dispatch(setAchievementPopupVisible(true));
                    }
                    dispatch(getCardsLeft());
                    dispatch(getGameStats());
                }
            )
    };
};


export const checkIfCollectionCompleted = (collectionId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(setAchievementDone(null));

        let url = apiConfig.url + "/profile/checkIfCollectionCompleted";
        let authHeader = "Bearer " + token;
        let bodyStr = "accountId=" + accountId + "&collectionId=" + collectionId;
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
            })
            .then(
                response => {
                    return response.json();
                }
            )
            .then(
                response => {
                    if (response != null && response.id != null) {
                        dispatch(setAchievementDone(response));
                        dispatch(setAchievementPopupVisible(true));
                    }
                    dispatch(getCardsLeft());
                    dispatch(getGameStats());
                }
            )
    };
};