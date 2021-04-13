package com.greenfox.restapi.models;


public class DoublingNumber {

    private int received;
    private int result;
    private String error;

    public DoublingNumber(int received, int result) {
        this.received = received;
        this.result = result;

    }

    public DoublingNumber(String error) {
        this.error = error;
    }


    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
