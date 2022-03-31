package com.example.oauth2jwt.security.oauth2.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class LoginRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;
}
