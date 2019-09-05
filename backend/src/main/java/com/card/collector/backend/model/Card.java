package com.card.collector.backend.model;

import javax.persistence.*;

@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title", nullable = false)
    private String title;

    @Lob
    @Column(name="description")
    private String description;

    @Column(name="imageUrl")
    private String imageUrl;

    @Column(name="points")
    private Integer points;

    @Column(name="collectionId")
    private Integer collectionId;

    @Enumerated(EnumType.STRING)
    private CardClass cardClass;

    public Card() {    }

    public Card(String title, String description, String imageUrl, Integer points, Integer collectionId, CardClass cardClass) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.points = points;
        this.collectionId = collectionId;
        this.cardClass = cardClass;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public CardClass getCardClass() {
        return cardClass;
    }

    public void setCardClass(CardClass cardClass) {
        this.cardClass = cardClass;
    }
}
