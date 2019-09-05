package com.card.collector.backend.repository;

import com.card.collector.backend.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
