package com.greenfox.restapi.models;

public class Text {

    private String text;

    public Text(String text) {
        this.text = text;
    }

    public Text() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
