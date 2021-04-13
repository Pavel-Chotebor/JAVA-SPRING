package com.messageservice.messageservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


public interface MessageService {
    public String send (String message, String sendBy);
}
