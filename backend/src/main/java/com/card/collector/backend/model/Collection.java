package com.card.collector.backend.model;

import javax.persistence.*;

@Entity
@Table(name="collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="numOfCards")
    private Integer numOfCards=0;

    @Column(name="capImageUrl")
    private String capImageUrl;

    @OneToOne
    @JoinColumn(name = "collectionCategoryId", referencedColumnName = "id")
    private CollectionCategory collectionCategory;

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

    public String getCapImageUrl() {
        return capImageUrl;
    }

    public void setCapImageUrl(String capImageUrl) {
        this.capImageUrl = capImageUrl;
    }

    public CollectionCategory getCollectionCategory() {
        return collectionCategory;
    }

    public void setCollectionCategory(CollectionCategory collectionCategory) {
        this.collectionCategory = collectionCategory;
    }

    public Integer getNumOfCards() {
        return numOfCards;
    }

    public void setNumOfCards(Integer numOfCards) {
        this.numOfCards = numOfCards;
    }
}