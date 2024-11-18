package com.taa.laf.client.dto;

import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;

    public User() {

    }

    public User(UUID userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

}
