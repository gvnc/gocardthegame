package com.card.collector.backend.repository;

import com.card.collector.backend.model.Card;
import com.card.collector.backend.model.CardClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {

    List<Card> findByCollectionId(Integer collectionId);

    List<Card> findByCollectionIdAndCardClass(Integer collectionId, CardClass cardClass);

    @Query("select c from Card c where c.collectionId = ?2 and c.cardClass = ?3 and c.id not in (select ac.card.id from AccountCard ac where ac.accountId =?1)")
    List<Card> findNewCardOnlyByCollectionIdAndCardClass(Integer accountId, Integer collectionId, CardClass cardClass);
}
