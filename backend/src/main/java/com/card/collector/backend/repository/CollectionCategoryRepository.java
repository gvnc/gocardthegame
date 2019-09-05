package com.card.collector.backend.repository;

import com.card.collector.backend.model.CollectionCategory;
import org.springframework.data.repository.CrudRepository;

public interface CollectionCategoryRepository extends CrudRepository<CollectionCategory, Integer> {

    CollectionCategory getByTitle(String title);
}
