package com.card.collector.backend.repository;

import com.card.collector.backend.model.AccountCard;
import com.card.collector.backend.model.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountCardRepository extends CrudRepository<AccountCard, Integer> {

    Optional<AccountCard> findByAccountIdAndCardId(Integer accountId, Integer cardId);

    @Query("select distinct ac.card from AccountCard ac where ac.accountId =?1 and ac.card.collectionId = ?2")
    List<Card> findByAccountIdAndCollectionId(Integer accountId, Integer collectionId);

    @Query("select count(distinct ac.card) from AccountCard ac where ac.accountId =?1 and ac.card.collectionId = ?2")
    Integer getCardCountByAccountIdAndCollectionId(Integer accountId, Integer collectionId);

    @Query("select distinct ac.card.id from AccountCard ac where ac.accountId =?1 and ac.card.collectionId = ?2")
    List<Integer> findDistinctCardIdsByAccountIdAncollectionId(Integer accountId, Integer collectionId);
}
