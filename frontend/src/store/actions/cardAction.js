import {
    OPEN_RANDOM_CARD,
    CLOSE_CARD_SHOW,
    GET_CARDS_LEFT,
    GET_CARDS_BY_ACCOUNT_AND_COLLECTION,
    ADD_NEW_CARD
} from './actionTypes';
import { uiStartLoading, uiStopLoading } from "./uiLoadingActions";

import apiConfig from './apiConfig';

export const openRandomCard = (collectionId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(uiStartLoading("openCard"));

        let url = apiConfig.url + "/cards/openRandomCard";
        let authHeader = "Bearer " + token;
        
        dispatch(setOpenedCard(null));

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
                dispatch(uiStopLoading("openCard"));
            })
            .then(response => response.json())
            .then(
                response => {

                    const openedCardIdList = getState().card.openedCardIdList;
                    var openedCardIdsObject = openedCardIdList.find(function (openedCardsObj) {
                        return openedCardsObj.collectionId === collectionId;
                    });
                    if(openedCardIdsObject !== undefined && openedCardIdsObject !== null) {
                        dispatch(addNewCard(response));
                    } else {
                        dispatch(getCardsByAccountAndCollection(collectionId));
                    }
                    dispatch(setOpenedCard(response));
                    dispatch(uiStopLoading("openCard"));
                }
            )
    };
}

export const addNewCard = (card) => {
    return {
        type: ADD_NEW_CARD,
        collectionId: card.collectionId,
        cardId: card.id
    };
};

export const setOpenedCard = (openedCard) => {
    return {
        type: OPEN_RANDOM_CARD,
        openedCard: openedCard
    };
};

export const closeCardShow = () => {
    return {
        type: CLOSE_CARD_SHOW
    };
};

export const openCardOfClass = (collectionId, cardClass) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(uiStartLoading("openCard"));

        let url = apiConfig.url + "/cards/openCardOfClass";
        let authHeader = "Bearer " + token;
        
        dispatch(setOpenedCard(null));

        let bodyStr = "accountId=" + accountId + "&collectionId=" + collectionId + "&cardClass=" + cardClass;
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
                dispatch(uiStopLoading("openCard"));
            })
            .then(response => response.json())
            .then(
                response => {
                    const openedCardIdList = getState().card.openedCardIdList;
                    var openedCardIdsObject = openedCardIdList.find(function (openedCardsObj) {
                        return openedCardsObj.collectionId === collectionId;
                    });
                    if(openedCardIdsObject !== undefined && openedCardIdsObject !== null) {
                        dispatch(addNewCard(response));
                    } else {
                        dispatch(getCardsByAccountAndCollection(collectionId));
                    }
                    dispatch(setOpenedCard(response));
                    dispatch(uiStopLoading("openCard"));
                }
            )
    };
}

export const getCardsLeft = () => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        let url = apiConfig.url + "/cards/getCardsLeft";
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
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setCardsLeft(response));
                }
            )
    };
}

export const setCardsLeft = (cardsLeft) => {
    return {
        type: GET_CARDS_LEFT,
        cardsLeft: cardsLeft
    };
};

export const getCardsByAccountAndCollection = (collectionId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        let url = apiConfig.url + "/cards/getCardIdsByCollectionId";
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
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setCardsByAccountAndCollection(response, collectionId));
                }
            )
    };
}

export const setCardsByAccountAndCollection = (cardIds, collectionId) => {
    return {
        type: GET_CARDS_BY_ACCOUNT_AND_COLLECTION,
        cardIds: cardIds,
        collectionId: collectionId
    };
};

export const getRewardCards = (cardClass) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        //dispatch(uiStartLoading("openCard"));

        let url = apiConfig.url + "/cards/getRewardCards";
        let authHeader = "Bearer " + token;
        
        dispatch(setOpenedCard(null));

        let bodyStr = "accountId=" + accountId + "&cardClass=" + cardClass;
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
             //   dispatch(uiStopLoading("openCard"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setCardsLeft(response));
                  //  dispatch(uiStopLoading("openCard"));
                }
            )
    };
}