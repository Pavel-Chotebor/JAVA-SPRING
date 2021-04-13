package com.greenfox.restapi.models;

public class Word {

    private String appended;

    public Word(String appended) {
        this.appended = appended;
    }

    public String getAppended() {
        return appended;
    }

    public void setAppended(String appended) {
        this.appended = appended;
    }
}
