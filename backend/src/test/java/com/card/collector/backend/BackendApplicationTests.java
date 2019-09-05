package com.card.collector.backend;

import com.card.collector.backend.model.Account;
import com.card.collector.backend.model.Card;
import com.card.collector.backend.repository.AccountRepository;
import com.card.collector.backend.repository.AchievementRepository;
import com.card.collector.backend.service.CardService;
import com.card.collector.backend.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class BackendApplicationTests {

	@Autowired
	CardService cardService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AchievementRepository achievementRepository;

	@Autowired
	ProfileService profileService;

	@Test
	public void findCardsByAccountIdCollectionId() {
		Map<Integer,Card> cardList = cardService.findCardsByAccountIdCollectionId(1,1);
		assert (!cardList.isEmpty());
	}

	@Test
	public void getGameStats() {
		Account account = accountRepository.findById(1).orElse(null);
		assert (account.getGameStats().getTotalPoints() > 0);
	}

	@Test
	public void getAccountAchievement() {
		assert (profileService.isAchievementDone(1,1) != null);
	}
}
