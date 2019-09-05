import { OPEN_RANDOM_CARD, CLOSE_CARD_SHOW, GET_CARDS_LEFT, GET_CARDS_BY_ACCOUNT_AND_COLLECTION, ADD_NEW_CARD } from "../actions/actionTypes";

const initialState = {
    openedCard: null,
    showOpenedCard: false,
    cardsLeft: null,
    openedCardIdList: []
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case OPEN_RANDOM_CARD:
            return {
                ...state,
                openedCard: action.openedCard,
                showOpenedCard: true
            };
        case CLOSE_CARD_SHOW:
            return {
                ...state,
                showOpenedCard: false
            };
        case GET_CARDS_LEFT:
            return {
                ...state,
                cardsLeft: action.cardsLeft
            };
        case GET_CARDS_BY_ACCOUNT_AND_COLLECTION:
            var openedCardIdsObject = state.openedCardIdList.find(function(openedCardIdsObj){
                return openedCardIdsObj.collectionId === action.collectionId
            });
            if(openedCardIdsObject !== undefined && openedCardIdsObject !== null)
                return state;
            return {
                ...state,
                openedCardIdList: [
                    ...state.openedCardIdList,
                    {
                        collectionId: action.collectionId,
                        cardIds: action.cardIds
                    }
                ]
            };
        case ADD_NEW_CARD:
            // do not add new card if already added before
            var indexOfList = -1;
            var openedCardIdsObject = state.openedCardIdList.find(function(openedCardIdsObj, index){
                if(openedCardIdsObj.collectionId === action.collectionId){
                    indexOfList = index;
                    return true;
                }
                return false;
            });
            var cardIds = [];
            if(openedCardIdsObject !== undefined && openedCardIdsObject !== null) {
                cardIds = openedCardIdsObject.cardIds;
                if(cardIds.indexOf(action.cardId) > -1)
                    return state;
            }

            var openedCardIdList = [...state.openedCardIdList];
            openedCardIdList.splice(indexOfList, 1);
            openedCardIdsObject.cardIds.push(action.cardId);
            openedCardIdList.push(openedCardIdsObject);

            return {
                ...state,
                openedCardIdList: openedCardIdList
            };
        default:
            return state;
    }
};

export default reducer;  