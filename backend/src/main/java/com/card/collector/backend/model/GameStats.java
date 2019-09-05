package com.card.collector.backend.model;

import javax.persistence.*;

/**
 * Created by EXT01D3678 on 19.09.2018.
 */
@Entity
@Table(name="gameStats")
public class GameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="totalPoints", nullable = false)
    private Integer totalPoints=0;

    @Column(name="totalCardsOpened", nullable = false)
    private Integer totalCardsOpened=0;

    @Column(name="totalCommonCardsOpened", nullable = false)
    private Integer totalCommonCardsOpened=0;

    @Column(name="totalRareCardsOpened", nullable = false)
    private Integer totalRareCardsOpened=0;

    @Column(name="totalUniqueCardsOpened", nullable = false)
    private Integer totalUniqueCardsOpened=0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getTotalCardsOpened() {
        return totalCardsOpened;
    }

    public void setTotalCardsOpened(Integer totalCardsOpened) {
        this.totalCardsOpened = totalCardsOpened;
    }

    public Integer getTotalCommonCardsOpened() {
        return totalCommonCardsOpened;
    }

    public void setTotalCommonCardsOpened(Integer totalCommonCardsOpened) {
        this.totalCommonCardsOpened = totalCommonCardsOpened;
    }

    public Integer getTotalRareCardsOpened() {
        return totalRareCardsOpened;
    }

    public void setTotalRareCardsOpened(Integer totalRareCardsOpened) {
        this.totalRareCardsOpened = totalRareCardsOpened;
    }

    public Integer getTotalUniqueCardsOpened() {
        return totalUniqueCardsOpened;
    }

    public void setTotalUniqueCardsOpened(Integer totalUniqueCardsOpened) {
        this.totalUniqueCardsOpened = totalUniqueCardsOpened;
    }
}