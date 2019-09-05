package com.card.collector.backend.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="fullname")
    private String fullname;

    @Column(name="password")
    private String password;

    @Column(name="role", nullable = false)
    private String role;

    @Column(name="accountId", nullable = false)
    private Integer accountId;

    @Column(name="fbValidated", nullable = false)
    private Boolean fbValidated;

    @Column(name="signInType", nullable = false)
    private String signInType = SignInType.REGULAR.toString();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Boolean getFbValidated() {
        return fbValidated;
    }

    public void setFbValidated(Boolean fbValidated) {
        this.fbValidated = fbValidated;
    }

    public String getSignInType() {
        return signInType;
    }

    public void setSignInType(String signInType) {
        this.signInType = signInType;
    }
}
