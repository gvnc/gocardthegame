package com.card.collector.backend.model;

/**
 * Created by EXT01D3678 on 5.09.2018.
 */
public class OperationResponse {

    private boolean result;

    private String message;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
