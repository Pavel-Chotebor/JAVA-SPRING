package com.greenfox.peer2peerchat.models;

public class RegisterChatUserDTO {
    private String username;
    private int userId;

    public RegisterChatUserDTO(String username, int userId) {
        this.username = username;
        this.userId = userId;
    }

    public RegisterChatUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
