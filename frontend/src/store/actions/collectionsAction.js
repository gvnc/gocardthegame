import {
    GET_ALL_CATEGORIES, SET_SELECTED_CATEGORY, GET_ALL_COLLECTIONS, GET_COLLECTIONS_BY_CATEGORY,
    SET_OPENED_COLLECTION, SET_CARDS_FOR_OPENED_COLLECTION, SET_FAVORITE_COLLECTIONS,
    ADD_COLLECTION_TO_FAVORITES, REMOVE_COLLECTION_FROM_FAVORITES
} from './actionTypes';
import { uiStartLoading, uiStopLoading } from "./uiLoadingActions";
import NavigationService from './NavigationService';
import apiConfig from './apiConfig';
import {addNewCard, getCardsByAccountAndCollection} from './cardAction';

export const getAllCategories = () => {

    return (dispatch, getState) => {

        const token = getState().auth.token;

      //  dispatch(uiStartLoading());

        let url = apiConfig.url + "/collections/getCollectionCategoryList";
        let authHeader = "Bearer " + token;
        fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': authHeader
            }
        })
            .catch(err => {
                alert("error:" + err);
        //        dispatch(uiStopLoading());
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setCategoryList(response));
          //          dispatch(uiStopLoading());
                    dispatch(getCollectionsByCategory(0));
                    dispatch(setSelectedCategoryResponse(0, 'All'));
                }
            )
    };
}

export const setCategoryList = (categoryList) => {
    return {
        type: GET_ALL_CATEGORIES,
        categoryList: categoryList
    };
};

export const getCollectionsByCategory = (categoryId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;

        dispatch(uiStartLoading("availableCategories"));

        let url = apiConfig.url + "/collections/getCollectionsByCategoryId";
        let authHeader = "Bearer " + token;
        let bodyStr = "categoryId=" + categoryId;
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
                dispatch(uiStopLoading("availableCategories"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setAllCollectionList(response));
                    dispatch(setAvailableCollectionList(response));
                    dispatch(uiStopLoading("availableCategories"));
                }
            )
    };
}

export const setAllCollectionList = (collectionList) => {
    return {
        type: GET_ALL_COLLECTIONS,
        collectionList: collectionList
    };
};

export const setAvailableCollectionList = (collectionList) => {
    return {
        type: GET_COLLECTIONS_BY_CATEGORY,
        collectionList: collectionList
    };
};

export const setSelectedCategory = (categoryId, categoryTitle) => {

    return (dispatch, getState) => {

        const selectedCategory = getState().collections.selectedCategory;

        if (selectedCategory !== categoryId) {
            const allCollectionList = getState().collections.allCollections;
            if (categoryId === 0) {
                dispatch(setAvailableCollectionList(allCollectionList));
            } else {
                const availableCollectionList = allCollectionList.filter(
                    function(collection){
                        return collection.collectionCategory.id === categoryId;
                    }
                );
                dispatch(setAvailableCollectionList(availableCollectionList));
            }
            dispatch(setSelectedCategoryResponse(categoryId, categoryTitle));
        }
    };
}

export const setSelectedCategoryResponse = (categoryId, categoryTitle) => {
    return {
        type: SET_SELECTED_CATEGORY,
        selectedCategory: {
            id: categoryId,
            title: categoryTitle
        }
    };
}

export const openSelectedCollection = (collectionId, collectionTitle) => {
    return (dispatch, getState) => {
        dispatch(setOpenedCollection(collectionId, collectionTitle));
        var collectionObjectList = getState().collections.collectionObjectList;
        var collectionObject = collectionObjectList.find(function(collectionObjectD){
            return collectionObjectD.id === collectionId
        });
        if(collectionObject === undefined || collectionObject === null) {
            dispatch(getCardsByCollectionId(collectionId, collectionTitle));
        } else {
            NavigationService.navigate("CollectionView");
        }

        const openedCardIdList = getState().card.openedCardIdList;
        var openedCardIdsObject = openedCardIdList.find(function (openedCardsObj) {
            return openedCardsObj.collectionId === collectionId;
        });
        if(openedCardIdsObject === undefined || openedCardIdsObject === null) {
            dispatch(getCardsByAccountAndCollection(collectionId));
        }
    };
}

export const setOpenedCollection = (collectionId, collectionTitle) => {
    return {
        type: SET_OPENED_COLLECTION,
        openedCollection: {
            id: collectionId,
            title: collectionTitle
        }
    };
}

export const getCardsByCollectionId = (collectionId, collectionTitle) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;

    //    dispatch(uiStartLoading());

        let url = apiConfig.url + "/cards/getCardsByCollectionId";
        let authHeader = "Bearer " + token;
        let bodyStr = "collectionId=" + collectionId;
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
       //         dispatch(uiStopLoading());
            })
            .then(response => response.json())
            .then(
                response => {
		            NavigationService.navigate("CollectionView");
                    dispatch(setCardsForOpenedCollection(collectionId, collectionTitle, response));
         //           dispatch(uiStopLoading());
                }
            )
    };
}

export const setCardsForOpenedCollection = (collectionId, collectionTitle, cardsList) => {
    return {
        type: SET_CARDS_FOR_OPENED_COLLECTION,
        cards: cardsList,
        collectionId: collectionId,
        collectionTitle: collectionTitle
    };
}

export const getFavoriteCollections = () => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        dispatch(uiStartLoading("favoriteCollections"));

        let url = apiConfig.url + "/collections/getFavoriteCollections";
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
                dispatch(uiStopLoading("favoriteCollections"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setFavoriteCollections(response));
                    dispatch(uiStopLoading("favoriteCollections"));
                }
            )
    };
}

export const setFavoriteCollections = (collectionList) => {
    return {
        type: SET_FAVORITE_COLLECTIONS,
        collectionList: collectionList
    };
}


export const addCollectionToFavorites = (collectionId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        // dispatch(uiStartLoading("favoriteCollections"));

        let url = apiConfig.url + "/collections/addCollectionToAccount";
        let bodyStr = "accountId=" + accountId + '&collectionId=' + collectionId;
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
                // dispatch(uiStopLoading("favoriteCollections"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setCollectionToFavorites(response));
                    // dispatch(uiStopLoading("favoriteCollections"));
                }
            )
    };
}


export const setCollectionToFavorites = (response) => {
    return {
        type: ADD_COLLECTION_TO_FAVORITES,
        accountCollection: response
    };
}


export const removeCollectionFromFavorites = (collectionId) => {

    return (dispatch, getState) => {

        const token = getState().auth.token;
        const accountId = getState().auth.accountId;

        // dispatch(uiStartLoading("favoriteCollections"));

        let url = apiConfig.url + "/collections/removeCollectionFromFavorites";
        let bodyStr = "accountId=" + accountId + '&collectionId=' + collectionId;
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
                // dispatch(uiStopLoading("favoriteCollections"));
            })
            .then(response => response.json())
            .then(
                response => {
                    dispatch(setRemovedCollection(response));
                    // dispatch(uiStopLoading("favoriteCollections"));
                }
            )
    };
}

export const setRemovedCollection = (response) => {
    return {
        type: REMOVE_COLLECTION_FROM_FAVORITES,
        accountCollection: response
    };
}