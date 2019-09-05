package com.card.collector.backend.repository;

import com.card.collector.backend.model.AccountAchievement;
import com.card.collector.backend.model.Achievement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountAchievementRepository extends CrudRepository<AccountAchievement, Integer> {

    @Query("select aa.achievement from AccountAchievement aa where aa.accountId =?1")
    List<Achievement> getAchievementsByAccountId(Integer accountId);

    Optional<AccountAchievement> findByAccountIdAndAchievementId(Integer accountId, Integer achievementId);
}
