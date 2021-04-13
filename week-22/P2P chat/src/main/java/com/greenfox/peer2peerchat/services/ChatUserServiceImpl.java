package com.greenfox.peer2peerchat.services;

import com.greenfox.peer2peerchat.models.ChatUser;
import com.greenfox.peer2peerchat.models.RegisterChatUserDTO;
import io.netty.handler.codec.http2.Http2Headers;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ChatUserServiceImpl implements ChatUserService{

    private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    @Override
    public RegisterChatUserDTO registerChatUser(ChatUser chatUser, String path) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apiKey", "myKey");

        HttpEntity<ChatUser> chatUserHttpEntity = new HttpEntity<>(chatUser, httpHeaders);
        ResponseEntity<RegisterChatUserDTO> responce = restTemplateBuilder
                .build()
                .exchange(getUri(path), HttpMethod.POST, chatUserHttpEntity, RegisterChatUserDTO.class);

        return responce.getBody();
    }

    private URI getUri (String path) {
        UriBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(System.getenv("RASCAL_API_URL")).path(path);

        return uriBuilder.build();
    }
}
