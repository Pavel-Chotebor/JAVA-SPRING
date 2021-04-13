package com.greenfox.restapi.models;

public class InputNumber {

    private Integer until;

    public InputNumber() {
    }

    public InputNumber(Integer until) {
        this.until=until;

    }

    public Integer getUntil() {
        return until;
    }

    public void setUntil(Integer until) {
        this.until = until;
    }

}
