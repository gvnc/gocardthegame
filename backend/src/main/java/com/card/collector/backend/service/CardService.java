package com.card.collector.backend.service;

import com.card.collector.backend.model.*;
import com.card.collector.backend.repository.AccountCardRepository;
import com.card.collector.backend.repository.AccountCollectionRepository;
import com.card.collector.backend.repository.AccountRepository;
import com.card.collector.backend.repository.CardRepository;
import com.card.collector.backend.utility.ApplicationParameters;
import com.card.collector.backend.utility.DatabaseUtility;
import com.card.collector.backend.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CardService {

    Logger logger = LoggerFactory.getLogger(CardService.class);

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountCardRepository accountCardRepository;

    @Autowired
    private AccountCollectionRepository accountCollectionRepository;

    private long oneDayTimeInMillis = 86400000;

    public List<Card> findCardsByCollectionId(Integer collectionId) {
        try {
            return cardRepository.findByCollectionId(collectionId);
        } catch (Exception e) {
            throw new RuntimeException("Can not find cards");
        }
    }

    public Card findCardById(Integer cardId) {
        try {
            return cardRepository.findById(cardId).orElse(null);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Can not find cards");
        }
    }

    public Map<Integer, Card> findCardsByAccountIdCollectionId(Integer accountId, Integer collectionId) {
        try {
            List<Card> cardList = accountCardRepository.findByAccountIdAndCollectionId(accountId, collectionId);
            Map<Integer, Card> cardMap = cardList.stream().collect(
                    Collectors.toMap(Card::getId, Function.identity()));
            return cardMap;
        } catch (Exception e) {
            throw new RuntimeException("Can not find cards");
        }
    }

    public List<Integer> findDistinctCardIdsByAccountIdAncollectionId(Integer accountId, Integer collectionId) {
        try {
            return accountCardRepository.findDistinctCardIdsByAccountIdAncollectionId(accountId, collectionId);
        } catch (Exception e) {
            throw new RuntimeException("Can not find cards");
        }
    }

    public Card openCardOfSpecificClass(Integer accountId, Integer collectionId, String cardClassString) {
        return openCard(accountId, collectionId, CardClass.valueOf(cardClassString));
    }

    public Card openRandomCard(Integer accountId, Integer collectionId) {
        return openCard(accountId, collectionId, null);
    }

    public Card openCard(Integer accountId, Integer collectionId, CardClass cardClass) {
        try {
            boolean isItDailyCard = false;
            if(cardClass == null)
                isItDailyCard = true;

            Account account = accountRepository.findById(accountId).orElse(null);

            AccountCollection accountCollection =
                    accountCollectionRepository.findByAccountIdAndCollectionId(accountId, collectionId).orElse(new AccountCollection());

            if (isAllowedToOpenNewDailyCard(account) || isItDailyCard == false) {

                // get card class to open
                if(cardClass ==null)
                    cardClass = getCardClass(accountCollection);

                // check for new card constraint
                boolean isNewCardForced = checkForNewCardConstraint(accountCollection, cardClass);

                // getAvailableCards
                List availableCards = getAvailableCardsForPick(accountId, collectionId, cardClass, isNewCardForced );

                // pick a random card of a collection
                Card openedCard = pickRandomCard(availableCards);

                // update account collection parameters
                updateAccountCollection(accountCollection, openedCard);

                // update account-card table
                updateAccountCard(accountId, openedCard);

                // update account
                updateAccount(account, openedCard, isItDailyCard);

                return openedCard;
            }
            return null;
        } catch (Exception e) {
            logger.error("Can not open any card:", e);
            throw new RuntimeException("Can not open any card");
        }
    }

    private Boolean isAllowedToOpenNewDailyCard(Account account) {

        if (account.getLastDailyCardsRefreshDate() == null) {
            account.setLastDailyCardsRefreshDate(DatabaseUtility.getNowInTimestamp());
            account.setNumOfDailyCardsLeft(ApplicationParameters.dailyAllowedNumberOfCards);
            return true;
        }

        long lastDailyCardsRefreshDateInMillis = account.getLastDailyCardsRefreshDate().getTime();
        long todayStartInMillis = new Date().getTime();

        if (todayStartInMillis > lastDailyCardsRefreshDateInMillis + oneDayTimeInMillis) {
            account.setLastDailyCardsRefreshDate(DatabaseUtility.getNowInTimestamp());
            account.setNumOfDailyCardsLeft(ApplicationParameters.dailyAllowedNumberOfCards);
            return true;
        }

        int numOfDailyCardsLeft = account.getNumOfDailyCardsLeft();

        if (numOfDailyCardsLeft > 0)
            return true;

        return false;
    }

    private CardClass getCardClass(AccountCollection accountCollection){

        Integer randomNumber = Utility.getRandomNumber(10) + 1;

        // check for unique cards
        Integer uniqueCardProbability = accountCollection.getProbabilityOfUnique();
        Integer probabilityOfUnique = uniqueCardProbability * randomNumber;

        if(probabilityOfUnique > ApplicationParameters.uniqueCardPickThreshold){
            return CardClass.Unique;
        }

        // check for rare cards
        Integer rareCardProbability = accountCollection.getProbabilityOfRare();
        Integer probabilityOfRare = rareCardProbability * randomNumber;

        if(probabilityOfRare > ApplicationParameters.rareCardPickThreshold){
            return CardClass.Rare;
        }

        return CardClass.Common;
    }

    private boolean checkForNewCardConstraint(AccountCollection accountCollection, CardClass cardClass) {
        int thresholdDays;
        java.sql.Timestamp lastNewCardDate = null;
        switch (cardClass) {
            case Unique:
                thresholdDays = ApplicationParameters.newUniqueCardMaxDaysThreshold;
                lastNewCardDate = accountCollection.getLastNewCardForUnique();
                break;
            case Rare:
                thresholdDays = ApplicationParameters.newRareCardMaxDaysThreshold;
                lastNewCardDate = accountCollection.getLastNewCardForRare();
                break;
            default:
                thresholdDays = ApplicationParameters.newCommonCardMaxDaysThreshold;
                lastNewCardDate = accountCollection.getLastNewCardForCommon();
                break;
        }

        if(lastNewCardDate == null){
            return true;
        }

        Calendar thresholdDate = Calendar.getInstance();
        thresholdDate.add(Calendar.DAY_OF_YEAR,-1 * thresholdDays);

        long lastNewCardDateInMillis = lastNewCardDate.getTime();

        if(lastNewCardDateInMillis < thresholdDate.getTimeInMillis())
            return true;

        return false;
    }

    private List<Card> getAvailableCardsForPick(Integer accountId, Integer collectionId, CardClass cardClass, boolean isNewCardForced){
        if(isNewCardForced == true) {
            List<Card>resultList = cardRepository.findNewCardOnlyByCollectionIdAndCardClass(accountId, collectionId, cardClass);
            if(resultList.size() > 0)
                return resultList;
        }
        return cardRepository.findByCollectionIdAndCardClass(collectionId, cardClass);
    }

    private Card pickRandomCard(List<Card> availableCards) {
        Integer randomNumber = Utility.getRandomNumber(availableCards.size());
        return availableCards.get(randomNumber);
    }

    private void updateAccountCollection(AccountCollection accountCollection, Card openedCard) {
        AccountCard accountCard = accountCardRepository.findByAccountIdAndCardId(accountCollection.getAccountId(), openedCard.getId()).orElse(null);
        if(accountCard == null) {
            switch (openedCard.getCardClass()) {
                case Unique:
                    accountCollection.setLastNewCardForUnique(DatabaseUtility.getNowInTimestamp());
                    break;
                case Rare:
                    accountCollection.setLastNewCardForRare(DatabaseUtility.getNowInTimestamp());
                    break;
                default:
                    accountCollection.setLastNewCardForCommon(DatabaseUtility.getNowInTimestamp());
                    break;
            }
        }
        if(openedCard.getCardClass().compareTo(CardClass.Unique) != 0) {
            Integer newProbability = accountCollection.getProbabilityOfUnique() + openedCard.getPoints();
            accountCollection.setProbabilityOfUnique(newProbability);
        } else {
            accountCollection.setProbabilityOfUnique(0);
        }
        if(openedCard.getCardClass().compareTo(CardClass.Rare) != 0) {
            Integer newProbability = accountCollection.getProbabilityOfRare() + openedCard.getPoints();
            accountCollection.setProbabilityOfRare(newProbability);
        } else {
            accountCollection.setProbabilityOfRare(0);
        }
        accountCollectionRepository.save(accountCollection);
    }

    private void updateAccountCard(Integer accountId, Card openedCard) {
        AccountCard accountCard = accountCardRepository.findByAccountIdAndCardId(accountId, openedCard.getId()).orElse(new AccountCard());
        accountCard.setAccountId(accountId);
        accountCard.setCard(openedCard);
        int cardCount = accountCard.getCardCount() + 1;
        accountCard.setCardCount(cardCount);
        accountCardRepository.save(accountCard);
    }

    private void updateAccount(Account account, Card openedCard, boolean isItDailyCard) {
        if(isItDailyCard == true) {
            int numberOfCardsOpened = account.getNumOfDailyCardsLeft() - 1;
            account.setNumOfDailyCardsLeft(numberOfCardsOpened);
        } else {
            switch (openedCard.getCardClass()){
                case Unique:
                    account.setNumOfUniqueCardsLeft(account.getNumOfUniqueCardsLeft() - 1);
                    break;
                case Rare:
                    account.setNumOfRareCardsLeft(account.getNumOfRareCardsLeft() - 1);
                    break;
                default:
                    account.setNumOfCommonCardsLeft(account.getNumOfCommonCardsLeft() - 1);
                    break;
            }
        }
        GameStats gameStats = account.getGameStats();
        gameStats.setTotalPoints(gameStats.getTotalPoints() + openedCard.getPoints());
        gameStats.setTotalCardsOpened(gameStats.getTotalCardsOpened() + 1);
        switch (openedCard.getCardClass()){
            case Unique:
                gameStats.setTotalUniqueCardsOpened(gameStats.getTotalUniqueCardsOpened() + 1);
                break;
            case Rare:
                gameStats.setTotalRareCardsOpened(gameStats.getTotalRareCardsOpened() + 1);
                break;
            default:
                gameStats.setTotalCommonCardsOpened(gameStats.getTotalCommonCardsOpened() + 1);
                break;
        }

        accountRepository.save(account);
    }

    public Account getCardsLeft(Integer accountId) {
        try {
            Account account = accountRepository.findById(accountId).orElse(null);

            if (account.getLastDailyCardsRefreshDate() == null) {
                account.setLastDailyCardsRefreshDate(DatabaseUtility.getNowInTimestamp());
                account.setNumOfDailyCardsLeft(ApplicationParameters.dailyAllowedNumberOfCards);
                accountRepository.save(account);
                return account;
            }

            long lastDailyCardsRefreshDateInMillis = account.getLastDailyCardsRefreshDate().getTime();
            long todayStartInMillis = new Date().getTime();

            if (todayStartInMillis > lastDailyCardsRefreshDateInMillis + oneDayTimeInMillis) {
                account.setLastDailyCardsRefreshDate(DatabaseUtility.getNowInTimestamp());
                account.setNumOfDailyCardsLeft(account.getNumOfDailyCardsLeft() + ApplicationParameters.dailyAllowedNumberOfCards);
                accountRepository.save(account);
                return account;
            }

            return account;
        } catch (Exception e) {
            logger.error("Can not get cards left:", e);
            throw new RuntimeException("Can not get cards left.");
        }
    }

    public Account getRewardCards(Integer accountId, String cardClassString) {
        try {
            Account account = accountRepository.findById(accountId).orElse(null);
            if(cardClassString.equals("Daily")){
                account.setNumOfDailyCardsLeft(5);
                accountRepository.save(account);
            } else {
                CardClass cardClass = CardClass.valueOf(cardClassString);
                switch (cardClass) {
                    case Unique:
                        break;
                    case Rare:
                        break;
                    case Common:
                        break;
                    default:
                        break;
                }
            }
            return account;
        } catch (Exception e) {
            logger.error("Can not get rewarded cards:", e);
            throw new RuntimeException("Can not get rewarded cards.");
        }
    }
}