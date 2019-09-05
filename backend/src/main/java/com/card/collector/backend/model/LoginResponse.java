package com.card.collector.backend.model;

/**
 * Created by EXT01D3678 on 14.08.2018.
 */
public class LoginResponse {

    private boolean loginResult;
    private String loginMessage;
    private String token = null;
    private User user = null;

    public boolean isLoginResult() {
        return loginResult;
    }

    public void setLoginResult(boolean loginResult) {
        this.loginResult = loginResult;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
