package com.greenfox.peer2peerchat.controllers;

import com.greenfox.peer2peerchat.models.ChatUser;
import com.greenfox.peer2peerchat.services.ChatUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//
@RestController
public class MainController {

    private ChatUserService chatUserService;

    public MainController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register (@RequestBody ChatUser chatUser) {
        return new ResponseEntity<>(chatUserService.registerChatUser(chatUser, "/user/register"), HttpStatus.OK);


    }


}
