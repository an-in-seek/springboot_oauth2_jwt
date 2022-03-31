package com.example.oauth2jwt.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class JwtResponse {

    private final String type = "Bearer";
    private final String accessToken;
    private final String refreshToken;
    private final String id;
    private final String username;
    private final String email;
    private final List<String> roles;

    @Builder
    public JwtResponse(String accessToken, String refreshToken, String id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
