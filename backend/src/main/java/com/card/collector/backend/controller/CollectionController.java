package com.card.collector.backend.controller;

import com.card.collector.backend.model.AccountCollection;
import com.card.collector.backend.model.Collection;
import com.card.collector.backend.model.CollectionCategory;
import com.card.collector.backend.model.OperationResponse;
import com.card.collector.backend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @PostMapping("/getCollectionCategoryList")
    public List<CollectionCategory> getCollectionCategoryList() {
        return collectionService.findAllCategories();
    }

    @PostMapping("/getCollectionsByCategoryId")
    public List<Collection> getCollectionsByCategoryId(@RequestParam Integer categoryId) {
        return collectionService.findCollectionsByCategoryId(categoryId);
    }

    @PostMapping("/getFavoriteCollections")
    public List<AccountCollection> getFavoriteCollections(@RequestParam Integer accountId) {
        return collectionService.findCollectionsByAccountId(accountId);
    }

    @PostMapping("/addCollectionToAccount")
    public AccountCollection addCollectionToAccount(Integer accountId, Integer collectionId) {
        return collectionService.addCollectionToAccount(accountId, collectionId);
    }

    @PostMapping("/removeCollectionFromFavorites")
    public AccountCollection removeCollectionFromFavorites(Integer accountId, Integer collectionId) {
        return collectionService.removeCollectionFromFavorites(accountId, collectionId);
    }
}
