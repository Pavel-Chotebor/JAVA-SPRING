package com.messageservice.messageservice.configuration;

import com.messageservice.messageservice.service.EmailService;
import com.messageservice.messageservice.service.MessageService;
import com.messageservice.messageservice.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MessageProceeder{

    //@Autowired     field-based dependency injection
    private MessageService messageService;  // interface

    @Autowired    // constructor-based dependency injection
    public MessageProceeder ( MessageService messageService) {
       this.messageService = messageService;
    }


    public void processMessage (String message, String sendBy) {  //method for sending messages use in Main class
        System.out.println(messageService.send(message, sendBy)); //
    }
}
