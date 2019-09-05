package com.card.collector.backend.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationParameters {

    public static Integer dailyAllowedNumberOfCards;

    public static Integer rareCardPickThreshold;

    public static Integer uniqueCardPickThreshold;

    public static Integer newUniqueCardMaxDaysThreshold;

    public static Integer newRareCardMaxDaysThreshold;

    public static Integer newCommonCardMaxDaysThreshold;

    @Value("${daily.allowed.numberOf.cards}")
    public void setDailyAllowedNumberOfCards(Integer dailyAllowedNumberOfCards) {
        ApplicationParameters.dailyAllowedNumberOfCards = dailyAllowedNumberOfCards;
    }

    @Value("${rare.card.pick.threshold}")
    public void setRareCardPickThreshold(Integer rareCardPickThreshold) {
        ApplicationParameters.rareCardPickThreshold = rareCardPickThreshold;
    }

    @Value("${unique.card.pick.threshold}")
    public void setUniqueCardPickThreshold(Integer uniqueCardPickThreshold) {
        ApplicationParameters.uniqueCardPickThreshold = uniqueCardPickThreshold;
    }

    @Value("${new.unique.card.max.days.threshold}")
    public void setNewUniqueCardMaxDaysThreshold(Integer newUniqueCardMaxDaysThreshold) {
        ApplicationParameters.newUniqueCardMaxDaysThreshold = newUniqueCardMaxDaysThreshold;
    }

    @Value("${new.rare.card.max.days.threshold}")
    public void setNewRareCardMaxDaysThreshold(Integer newRareCardMaxDaysThreshold) {
        ApplicationParameters.newRareCardMaxDaysThreshold = newRareCardMaxDaysThreshold;
    }

    @Value("${new.common.card.max.days.threshold}")
    public void setNewCommonCardMaxDaysThreshold(Integer newCommonCardMaxDaysThreshold) {
        ApplicationParameters.newCommonCardMaxDaysThreshold = newCommonCardMaxDaysThreshold;
    }
}
