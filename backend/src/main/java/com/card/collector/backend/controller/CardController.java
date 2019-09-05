package com.card.collector.backend.controller;


import com.card.collector.backend.model.Account;
import com.card.collector.backend.model.Card;
import com.card.collector.backend.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cards")
public class CardController {

    Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    CardService cardService;

    @PostMapping("/getCardsLeft")
    public Account getCardsLeft(@RequestParam Integer accountId) {
        logger.debug("[getCardsLeft] accountId=" + accountId);
        return cardService.getCardsLeft(accountId);
    }

    @PostMapping("/openRandomCard")
    public Card openRandomCard(@RequestParam Integer accountId, @RequestParam Integer collectionId) {
        logger.debug("[getCardsLeft] accountId=" + accountId + ",collectionId=" + collectionId);
        return cardService.openRandomCard(accountId, collectionId);
    }

    @PostMapping("/openCardOfClass")
    public Card openCardOfClass(@RequestParam Integer accountId, @RequestParam Integer collectionId, @RequestParam String cardClass) {
        logger.debug("[getCardsLeft] accountId=" + accountId + ",collectionId=" + cardClass + ",collectionId=" + cardClass);
        return cardService.openCardOfSpecificClass(accountId, collectionId, cardClass);
    }

    @PostMapping("/getCardsByCollectionId")
    public List<Card> getCardsByCollectionId(@RequestParam Integer collectionId) {
        logger.debug("[getCardsLeft]  collectionId=" + collectionId);
        return cardService.findCardsByCollectionId(collectionId);
    }

    @PostMapping("/getCardsByAccountIdAndCollectionId")
    public Map<Integer,Card> getCardsByAccountIdAndCollectionId(@RequestParam Integer accountId, @RequestParam Integer collectionId) {
        logger.debug("[getCardsLeft] accountId=" + accountId + ",collectionId=" + collectionId);
        return cardService.findCardsByAccountIdCollectionId(accountId, collectionId);
    }

    @PostMapping("/getCardIdsByCollectionId")
    public List<Integer> getCardIdsByCollectionId(@RequestParam Integer accountId, @RequestParam Integer collectionId) {
        logger.debug("[getCardsLeft] accountId=" + accountId + ",collectionId=" + collectionId);
        return cardService.findDistinctCardIdsByAccountIdAncollectionId(accountId, collectionId);
    }

    @PostMapping("/getRewardCards")
    public Account getRewardCards(@RequestParam Integer accountId, @RequestParam String cardClass) {
        logger.debug("[getCardsLeft] accountId=" + accountId + ",cardClass=" + cardClass);
        return cardService.getRewardCards(accountId, cardClass);
    }
}