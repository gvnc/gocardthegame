package com.card.collector.backend.model;

import javax.persistence.*;

@Entity
@Table(name="accountAchievement")
public class AccountAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="accountId", nullable = false)
    private Integer accountId;

    @OneToOne
    @JoinColumn(name = "achievementId", referencedColumnName = "id")
    private Achievement achievement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }
}
