package com.greenfox.restapi.models;

public class Error {

    private String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
