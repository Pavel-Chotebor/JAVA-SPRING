package com.greenfoxacademy.demo.controllers;

import java.util.concurrent.atomic.AtomicLong;

public class Greeting {

    //long id;
    long counter;
    String content;


/*
    public Greeting (long id, String content) {
        this.id = id;
        this.content = content;
    }

 */

    public Greeting(long counter, String content) {
        this.counter = counter;
        this.content=content;
    }


/*
    public long getId() {
        return id;
    }

 */

    public long getCounter() {     // every get parameter is showed
        return counter;
    }


    public String getContent() {
        return content;
    }
}
