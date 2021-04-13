package com.greenfox.groot.models;


public class Error {

    private String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error() {
    }

    public String getError() {
        return errorMessage;
    }

    public void setError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
