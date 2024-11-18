package com.taa.laf.client;

import com.taa.laf.client.dto.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserClient {

    public User getUserByUsername(String username) {
        return new User(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), "Test User");
    }
}
