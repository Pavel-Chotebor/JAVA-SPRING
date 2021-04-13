package com.greenfox.restapi.models;

import java.util.List;

public class SithText {

    private String text;

    public SithText(String text) {
        this.text = text;
    }

    public SithText() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
