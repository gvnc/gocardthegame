package com.card.collector.backend.service;

import com.card.collector.backend.model.Account;
import com.card.collector.backend.model.AccountCollection;
import com.card.collector.backend.model.Collection;
import com.card.collector.backend.model.CollectionCategory;
import com.card.collector.backend.repository.AccountCollectionRepository;
import com.card.collector.backend.repository.AccountRepository;
import com.card.collector.backend.repository.CollectionCategoryRepository;
import com.card.collector.backend.repository.CollectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {

    Logger logger = LoggerFactory.getLogger(CollectionService.class);

    @Autowired
    private CollectionCategoryRepository collectionCategoryRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private AccountCollectionRepository accountCollectionRepository;

    public List<CollectionCategory> findAllCategories() {
        List<CollectionCategory> categoryList = new ArrayList<>();
        try {
            Iterable<CollectionCategory> iterable = collectionCategoryRepository.findAll();
            iterable.forEach(categoryList::add);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Can not find categories");
        }
        return categoryList;
    }

    public List<Collection> findCollectionsByCategoryId(Integer categoryId) {
        try {
            if(categoryId == 0) {
                List<Collection> collectionList = new ArrayList<>();
                Iterable<Collection> iterable = collectionRepository.findAll();
                iterable.forEach(collectionList::add);
                return collectionList;
            } else {
                CollectionCategory collectionCategory = new CollectionCategory();
                collectionCategory.setId(categoryId);
                return collectionRepository.findByCollectionCategory(collectionCategory);
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Can not find collections");
        }
    }

    public List<AccountCollection> findCollectionsByAccountId(Integer accountId) {
        try {
            return accountCollectionRepository.findByAccountIdAndDeleted(accountId, false);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Can not get collections");
        }
    }

    public AccountCollection addCollectionToAccount(Integer accountId, Integer collectionId) {
        try {
            AccountCollection accountCollection = accountCollectionRepository.findByAccountIdAndCollectionId(accountId, collectionId).orElse(null);
            if(accountCollection != null) {
                accountCollection.setDeleted(false);
                accountCollectionRepository.save(accountCollection);
                return accountCollection;
            }

            Collection collection = collectionRepository.findById(collectionId).get();

            accountCollection = new AccountCollection();
            accountCollection.setAccountId(accountId);
            accountCollection.setCollection(collection);
            accountCollectionRepository.save(accountCollection);

            return accountCollection;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Account is null");
        }
    }

    public AccountCollection removeCollectionFromFavorites(Integer accountId, Integer collectionId) {
        try {
            AccountCollection accountCollection = accountCollectionRepository.findByAccountIdAndCollectionId(accountId, collectionId).orElse(null);
            if(accountCollection != null) {
                accountCollection.setDeleted(true);
                accountCollectionRepository.save(accountCollection);
            }
            return accountCollection;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Account is null");
        }
    }
}