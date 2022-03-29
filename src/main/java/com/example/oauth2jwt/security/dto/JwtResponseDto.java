package com.example.oauth2jwt.security.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class JwtResponseDto {
    private String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    @Builder
    public JwtResponseDto(String accessToken, String refreshToken, String id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
