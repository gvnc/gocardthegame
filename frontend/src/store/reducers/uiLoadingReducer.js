
const initialState = {
    loadLocaleFile: false,
    signUpAnonymous: false,
    login: false,
    availableCategories:false,
    gameStats:false,
    allAchievements:false,
    isAchievementDone:false,
    favoriteCollections:false,
    openCard:false,
    signup:false
};

const reducer = (state = initialState, action) => {

    const { type } = action; // action.type ?
    const matches = /(.*)_(IS_LOADING|IS_LOADED)/.exec(type);

    if (!matches) return state;
    
    const [, requestName, requestState] = matches;

    return {
        ...state,
        [requestName]: requestState === 'IS_LOADING',
    };
};

export default reducer;  