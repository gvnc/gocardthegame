package com.card.collector.backend.model;


import javax.persistence.*;

@Entity
@Table(name="accountCard")
public class AccountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="accountId", nullable = false)
    private Integer accountId;

    @OneToOne
    @JoinColumn(name = "cardId", referencedColumnName = "id")
    private Card card;

    @Column(name="cardCount", nullable = false)
    private Integer cardCount=0;

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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getCardCount() {
        return cardCount;
    }

    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }
}
