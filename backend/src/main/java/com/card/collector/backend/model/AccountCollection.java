package com.card.collector.backend.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="accountCollection")
public class AccountCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="accountId", nullable = false)
    private Integer accountId;

    @OneToOne
    @JoinColumn(name = "collectionId", referencedColumnName = "id")
    private Collection collection;

    @Column(name="probabilityOfRare")
    private Integer probabilityOfRare=0;

    @Column(name="probabilityOfUnique")
    private Integer probabilityOfUnique=0;

    @Column(name="lastNewCardForCommon")
    private Timestamp lastNewCardForCommon;

    @Column(name="lastNewCardForRare")
    private Timestamp lastNewCardForRare;

    @Column(name="lastNewCardForUnique")
    private Timestamp lastNewCardForUnique;

    @Column(name="completed")
    private Boolean completed=false;

    @Column(name="deleted")
    private Boolean deleted=false;

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

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Integer getProbabilityOfRare() {
        return probabilityOfRare;
    }

    public void setProbabilityOfRare(Integer probabilityOfRare) {
        this.probabilityOfRare = probabilityOfRare;
    }

    public Integer getProbabilityOfUnique() {
        return probabilityOfUnique;
    }

    public void setProbabilityOfUnique(Integer probabilityOfUnique) {
        this.probabilityOfUnique = probabilityOfUnique;
    }

    public Timestamp getLastNewCardForCommon() {
        return lastNewCardForCommon;
    }

    public void setLastNewCardForCommon(Timestamp lastNewCardForCommon) {
        this.lastNewCardForCommon = lastNewCardForCommon;
    }

    public Timestamp getLastNewCardForRare() {
        return lastNewCardForRare;
    }

    public void setLastNewCardForRare(Timestamp lastNewCardForRare) {
        this.lastNewCardForRare = lastNewCardForRare;
    }

    public Timestamp getLastNewCardForUnique() {
        return lastNewCardForUnique;
    }

    public void setLastNewCardForUnique(Timestamp lastNewCardForUnique) {
        this.lastNewCardForUnique = lastNewCardForUnique;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
