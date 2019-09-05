package com.card.collector.backend.controller;

import com.card.collector.backend.model.AccountAchievement;
import com.card.collector.backend.model.Achievement;
import com.card.collector.backend.model.GameStats;
import com.card.collector.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by EXT01D3678 on 20.09.2018.
 */

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/getGameStatsByAccountId")
    public GameStats getGameStatsByAccountId(@RequestParam Integer accountId) {
        return profileService.getGameStatsByAccountId(accountId);
    }

    @PostMapping("/getAllAchievements")
    public List getAllAchievements() {
        return profileService.getAllAchievements();
    }

    @PostMapping("/isAchievementDone")
    public AccountAchievement isAchievementDone(@RequestParam Integer accountId, @RequestParam Integer achievementId) {
        return profileService.isAchievementDone(accountId, achievementId);
    }

    @PostMapping("/accomplishAchievementById")
    public AccountAchievement accomplishAchievementById(@RequestParam Integer accountId, @RequestParam Integer achievementId) {
        return profileService.accomplishAchievementById(accountId, achievementId);
    }

    @PostMapping("/checkIfAnyAchievementsAccomplished")
    public AccountAchievement checkIfAnyAchievementsAccomplished(@RequestParam Integer accountId) {
        return profileService.checkIfAnyAchievementsAccomplished(accountId);
    }

    @PostMapping("/checkIfCollectionCompleted")
    public AccountAchievement checkIfCollectionCompleted(@RequestParam Integer accountId, @RequestParam Integer collectionId) {
        return profileService.checkIfCollectionCompleted(accountId, collectionId);
    }
}
