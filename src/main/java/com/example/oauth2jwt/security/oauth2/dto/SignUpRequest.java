package com.example.oauth2jwt.security.oauth2.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private final String name;

    @Email
    @NotBlank
    @Size(max = 50)
    private final String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private final String password;
}
