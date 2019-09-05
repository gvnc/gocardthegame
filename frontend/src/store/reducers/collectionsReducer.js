import {
    GET_ALL_CATEGORIES, SET_SELECTED_CATEGORY, GET_ALL_COLLECTIONS, GET_COLLECTIONS_BY_CATEGORY, SET_OPENED_COLLECTION,
    SET_CARDS_FOR_OPENED_COLLECTION, SET_FAVORITE_COLLECTIONS, ADD_COLLECTION_TO_FAVORITES, REMOVE_COLLECTION_FROM_FAVORITES
} from "../actions/actionTypes";

const initialState = {
    categoryList: [],
    allCollections: [],
    availableCollections: [],
    selectedCategory: {
        id: 0,
        title: 'All'
    },
    openedCollectionId: {
        id: 0,
        title: ''
    },
    collectionObjectList: [],
    favoriteCollections: []
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case GET_ALL_CATEGORIES:
            return {
                ...state,
                categoryList: action.categoryList
            };
        case SET_SELECTED_CATEGORY:
            return {
                ...state,
                selectedCategory: action.selectedCategory
            };
        case GET_COLLECTIONS_BY_CATEGORY:
            return {
                ...state,
                availableCollections: action.collectionList
            };
        case GET_ALL_COLLECTIONS:
            return {
                ...state,
                allCollections: action.collectionList
            };
        case SET_OPENED_COLLECTION:
            return {
                ...state,
                openedCollection: {
                    id: action.openedCollection.id,
                    title: action.openedCollection.title
                }
            };
        case SET_CARDS_FOR_OPENED_COLLECTION:
            var collectionObject = state.collectionObjectList.find(function(collectionObject){
                return collectionObject.id === action.collectionId
            });
            if(collectionObject !== undefined && collectionObject !== null)
                return state;
            return {
                ...state,
                collectionObjectList: [
                    ...state.collectionObjectList,
                    {
                        id: action.collectionId,
                        title: action.collectionTitle,
                        cards: action.cards
                    }
                ]
            };
        case SET_FAVORITE_COLLECTIONS:
            return {
                ...state,
                favoriteCollections: action.collectionList
            };
        case ADD_COLLECTION_TO_FAVORITES:
            return {
                ...state,
                favoriteCollections: [...state.favoriteCollections, action.accountCollection]
            };
        case REMOVE_COLLECTION_FROM_FAVORITES:
            var favoriteCollectionsUpdated = state.favoriteCollections.filter(function(accountCollection){
                return accountCollection.collection.id !== action.accountCollection.collection.id
            });
            return {
                ...state,
                favoriteCollections: favoriteCollectionsUpdated
            };
        default:
            return state;
    }
};

export default reducer;  