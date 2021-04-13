package com.messageservice.messageservice.service;


public class TwitterService implements MessageService {


    @Override
    public String send(String message,String sendBy) {
        return "Twitter message: " + message + " send from: " + sendBy;
    }
}
