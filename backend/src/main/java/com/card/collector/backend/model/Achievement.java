package com.card.collector.backend.model;

import javax.persistence.*;

@Entity
@Table(name="achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="criteria")
    private String criteria;

    @Column(name="capImageUrl")
    private String capImageUrl;

    @Column(name="commonCardsAwarded")
    private Integer commonCardsAwarded;

    @Column(name="rareCardsAwarded")
    private Integer rareCardsAwarded;

    @Column(name="uniqueCardsAwarded")
    private Integer uniqueCardsAwarded;

    public Achievement() {
    }

    public Achievement(String title, String description, String criteria, String capImageUrl, Integer commonCardsAwarded, Integer rareCardsAwarded, Integer uniqueCardsAwarded) {
        this.title = title;
        this.description = description;
        this.criteria = criteria;
        this.capImageUrl = capImageUrl;
        this.commonCardsAwarded = commonCardsAwarded;
        this.rareCardsAwarded = rareCardsAwarded;
        this.uniqueCardsAwarded = uniqueCardsAwarded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCommonCardsAwarded() {
        return commonCardsAwarded;
    }

    public void setCommonCardsAwarded(Integer commonCardsAwarded) {
        this.commonCardsAwarded = commonCardsAwarded;
    }

    public Integer getRareCardsAwarded() {
        return rareCardsAwarded;
    }

    public void setRareCardsAwarded(Integer rareCardsAwarded) {
        this.rareCardsAwarded = rareCardsAwarded;
    }

    public Integer getUniqueCardsAwarded() {
        return uniqueCardsAwarded;
    }

    public void setUniqueCardsAwarded(Integer uniqueCardsAwarded) {
        this.uniqueCardsAwarded = uniqueCardsAwarded;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCapImageUrl() {
        return capImageUrl;
    }

    public void setCapImageUrl(String capImageUrl) {
        this.capImageUrl = capImageUrl;
    }
}