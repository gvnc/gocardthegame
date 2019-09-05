package com.card.collector.backend.service;

import com.card.collector.backend.model.*;
import com.card.collector.backend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class ProfileService {

    Logger logger = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private AccountAchievementRepository accountAchievementRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountCollectionRepository accountCollectionRepository;

    @Autowired
    private AccountCardRepository accountCardRepository;

    public List<Achievement> getAllAchievements(){
        List<Achievement> achievementList = new ArrayList<>();
        try {
            Iterable<Achievement> iterable = achievementRepository.findAll();
            iterable.forEach(achievementList::add);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Can not find achievements");
        }
        return achievementList;
    }

    public GameStats getGameStatsByAccountId(Integer accountId){
        Account account = accountRepository.findById(accountId).orElse(new Account());
        return account.getGameStats();
    }

    public List<Achievement> getAchievementsDoneByAccountId(Integer accountId){
        return accountAchievementRepository.getAchievementsByAccountId(accountId);
    }

    public AccountAchievement isAchievementDone(Integer accountId, Integer achievementId){
        return accountAchievementRepository.findByAccountIdAndAchievementId(accountId, achievementId).orElse(new AccountAchievement());
    }

    public AccountAchievement accomplishAchievementById(Integer accountId, Integer achievementId){

        AccountAchievement accountAchievementExist = accountAchievementRepository.findByAccountIdAndAchievementId(accountId, achievementId).orElse(null);
        if(accountAchievementExist != null)
            throw new RuntimeException("achievement already taken");

        Account account = accountRepository.findById(accountId).orElse(null);
        if(account == null)
            throw new RuntimeException("account not found");

        return accomplishAchievement(account, achievementId);
    }

    private AccountAchievement accomplishAchievement(Account account, Integer achievementId){

        Achievement achievement = achievementRepository.findById(achievementId).orElse(null);
        if(achievement == null)
            throw new RuntimeException("achievement not found");

        // update awarded cards on account
        account.setNumOfCommonCardsLeft(account.getNumOfCommonCardsLeft() + achievement.getCommonCardsAwarded());
        account.setNumOfRareCardsLeft(account.getNumOfRareCardsLeft() + achievement.getRareCardsAwarded());
        account.setNumOfUniqueCardsLeft(account.getNumOfUniqueCardsLeft() + achievement.getUniqueCardsAwarded());
        accountRepository.save(account);

        // set achievement as accomplished
        AccountAchievement accountAchievement = new AccountAchievement();
        accountAchievement.setAccountId(account.getId());
        accountAchievement.setAchievement(achievement);
        accountAchievementRepository.save(accountAchievement);

        return accountAchievement;
    }

    public AccountAchievement checkIfAnyAchievementsAccomplished(Integer accountId){
        Account account = accountRepository.findById(accountId).orElse(null);
        if(account == null)
            throw new RuntimeException("account not found");

        GameStats gameStats = account.getGameStats();

        Iterable<Achievement> achievements = achievementRepository.findAll();

        List<Achievement> existingAchievements = accountAchievementRepository.getAchievementsByAccountId(accountId);

        for(Achievement achievement:achievements) {
            String criteria = achievement.getCriteria();

            boolean achievementAlreadyTaken = false;
            for(Achievement existingAchievement:existingAchievements){
                if(achievement.getId() == existingAchievement.getId()){
                    achievementAlreadyTaken = true;
                    break;
                }
            }

            if(achievementAlreadyTaken==false && criteria != null) {
                try {
                    StringTokenizer stringTokenizer = new StringTokenizer(criteria, "=");
                    String statsName = stringTokenizer.nextToken();
                    Integer threshold = Integer.parseInt(stringTokenizer.nextToken());
                    String methodName = "get" + statsName;
                    Method m = GameStats.class.getMethod(methodName);
                    Integer currentValue = (Integer) m.invoke(gameStats);
                    if(currentValue >= threshold) {
                        return accomplishAchievementById(accountId, achievement.getId());
                    }
                } catch (Exception e) {
                    logger.error("Error in checkIfAnyAchievementsAccomplished." , e);
                }
            }
        }

        return new AccountAchievement();
    }

    public AccountAchievement checkIfCollectionCompleted(Integer accountId, Integer collectionId){

        Account account = accountRepository.findById(accountId).orElse(null);

        if(account == null) {
            logger.error("account not found");
            throw new RuntimeException("account not found");
        }

        AccountCollection accountCollection =
                accountCollectionRepository.findByAccountIdAndCollectionId(accountId, collectionId).orElse(null);

        if(account == null) {
            logger.error("collection is not favorite list");
            throw new RuntimeException("collection is not favorite list");
        }

        if(accountCollection.getCompleted() == true){
            logger.warn("collection already completed");
            return new AccountAchievement();
        }

        Integer cardCount = accountCardRepository.getCardCountByAccountIdAndCollectionId(accountId, collectionId);

        if(cardCount == accountCollection.getCollection().getNumOfCards()){
            accountCollection.setCompleted(true);
            accountCollectionRepository.save(accountCollection);

            return accomplishAchievement(account, 2);
        }

        return new AccountAchievement();
    }
}
