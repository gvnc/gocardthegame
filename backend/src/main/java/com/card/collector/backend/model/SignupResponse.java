package com.card.collector.backend.model;

/**
 * Created by EXT01D3678 on 14.08.2018.
 */
public class SignupResponse {

    private boolean signupResult;
    private String signupMessage;

    public boolean isSignupResult() {
        return signupResult;
    }

    public void setSignupResult(boolean signupResult) {
        this.signupResult = signupResult;
    }

    public String getSignupMessage() {
        return signupMessage;
    }

    public void setSignupMessage(String signupMessage) {
        this.signupMessage = signupMessage;
    }
}
