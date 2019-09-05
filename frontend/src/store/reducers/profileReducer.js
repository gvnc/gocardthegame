import {GET_GAME_STATS, GET_ALL_ACHIEVEMENTS, SET_ACHIEVEMENT_DONE, LOAD_LOCALE_FILE, SET_NEW_ACHIEVEMENT_STATE, SET_ACHIEVEMENT_POPUP } from "../actions/actionTypes";

const initialState = {
    isLocaleFileLoaded: false,
    gameStats: {
        totalPoints: 0,
        totalCommonCardsOpened: 0,
        totalRareCardsOpened: 0,
        totalUniqueCardsOpened: 0
    },
    allAchievements: [],
    accountAchievement: null,
    openAchievementPopup: false,
    newAchievementId: 0
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case GET_GAME_STATS:
            return {
                ...state,
                gameStats: action.gameStats
            };
        case GET_ALL_ACHIEVEMENTS:
            return {
                ...state,
                allAchievements: action.allAchievements
            };
        case SET_ACHIEVEMENT_DONE:
            return {
                ...state,
                accountAchievement: action.accountAchievement
            };
        case LOAD_LOCALE_FILE:
            return {
                ...state,
                isLocaleFileLoaded: action.isLocaleFileLoaded
            };
        case SET_NEW_ACHIEVEMENT_STATE:
            return {
                ...state,
                newAchievementId: action.newAchievementId
            };
        case SET_ACHIEVEMENT_POPUP:
            return {
                ...state,
                openAchievementPopup: action.openAchievementPopup
            };
        default:
            return state;
    }
};

export default reducer;  