package com.card.collector.backend.repository;

import com.card.collector.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Integer> {

    User getByEmail(String email);
    User getByAccountId(Integer accountId);
}
