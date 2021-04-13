package com.messageservice.messageservice.configuration;

import com.messageservice.messageservice.service.EmailService;
import com.messageservice.messageservice.service.TwitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    @Bean //Bean annotation is responsible for choosing
    EmailService emailService () {
        return new EmailService();
    }


    TwitterService twitterService () {
        return new TwitterService();
    }

}
