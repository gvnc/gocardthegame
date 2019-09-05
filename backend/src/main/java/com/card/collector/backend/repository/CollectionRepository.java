package com.card.collector.backend.repository;

import com.card.collector.backend.model.Collection;
import com.card.collector.backend.model.CollectionCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, Integer> {

    List<Collection> findByCollectionCategory(CollectionCategory category);

    Collection getByTitle(String title);
}
