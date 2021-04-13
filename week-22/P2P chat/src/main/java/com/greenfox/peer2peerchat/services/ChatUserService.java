package com.greenfox.peer2peerchat.services;

import com.greenfox.peer2peerchat.models.ChatUser;
import com.greenfox.peer2peerchat.models.RegisterChatUserDTO;
import org.springframework.stereotype.Service;

@Service
public interface ChatUserService {

    RegisterChatUserDTO registerChatUser (ChatUser chatUser, String path);
}
