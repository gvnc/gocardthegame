export const uiStartLoading = (actionType) => {
    return {
        type: actionType + "_IS_LOADING"
    };
};

export const uiStopLoading = (actionType) => {
    return {
        type: actionType + "_IS_LOADED"
    };
};