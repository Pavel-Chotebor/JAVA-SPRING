package com.messageservice.messageservice.service;

import org.springframework.stereotype.Service;


public class EmailService implements MessageService {


    @Override
    public String send(String message,String sendBy) {
        return "Email message: " + message + " send from: " + sendBy;
    }
}
