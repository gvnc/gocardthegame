package com.card.collector.backend.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="lastDailyCardsRefreshDate")
    private Timestamp lastDailyCardsRefreshDate;

    @Column(name="numOfDailyCardsLeft")
    private Integer numOfDailyCardsLeft =0;

    @Column(name="numOfCommonCardsLeft")
    private Integer numOfCommonCardsLeft =0;

    @Column(name="numOfRareCardsLeft")
    private Integer numOfRareCardsLeft =0;

    @Column(name="numOfUniqueCardsLeft")
    private Integer numOfUniqueCardsLeft =0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gameStatsId", referencedColumnName = "id")
    private GameStats gameStats = new GameStats();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getLastDailyCardsRefreshDate() {
        return lastDailyCardsRefreshDate;
    }

    public void setLastDailyCardsRefreshDate(Timestamp lastDailyCardsRefreshDate) {
        this.lastDailyCardsRefreshDate = lastDailyCardsRefreshDate;
    }

    public Integer getNumOfDailyCardsLeft() {
        return numOfDailyCardsLeft;
    }

    public void setNumOfDailyCardsLeft(Integer numOfDailyCardsLeft) {
        this.numOfDailyCardsLeft = numOfDailyCardsLeft;
    }

    public Integer getNumOfCommonCardsLeft() {
        return numOfCommonCardsLeft;
    }

    public void setNumOfCommonCardsLeft(Integer numOfCommonCardsLeft) {
        this.numOfCommonCardsLeft = numOfCommonCardsLeft;
    }

    public Integer getNumOfRareCardsLeft() {
        return numOfRareCardsLeft;
    }

    public void setNumOfRareCardsLeft(Integer numOfRareCardsLeft) {
        this.numOfRareCardsLeft = numOfRareCardsLeft;
    }

    public Integer getNumOfUniqueCardsLeft() {
        return numOfUniqueCardsLeft;
    }

    public void setNumOfUniqueCardsLeft(Integer numOfUniqueCardsLeft) {
        this.numOfUniqueCardsLeft = numOfUniqueCardsLeft;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }
}
