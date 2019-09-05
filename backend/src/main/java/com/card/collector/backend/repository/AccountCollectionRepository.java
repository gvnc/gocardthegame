package com.card.collector.backend.repository;

import com.card.collector.backend.model.AccountCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountCollectionRepository extends CrudRepository<AccountCollection, Integer> {

    Optional<AccountCollection> findByAccountIdAndCollectionId(Integer accountId, Integer collectionId);

    List<AccountCollection> findByAccountId(Integer accountId);

    List<AccountCollection> findByAccountIdAndDeleted(Integer accountId, Boolean deleted);

    List<AccountCollection> findByAccountIdAndCompleted(Integer accountId, Boolean completed);
}
