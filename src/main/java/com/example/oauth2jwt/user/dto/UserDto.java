package com.example.oauth2jwt.user.dto;

import com.example.oauth2jwt.security.oauth2.enums.SocialType;
import com.example.oauth2jwt.user.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
public class UserDto {

    private final Long id;

    private final String username;

    @JsonIgnore
    private final String password;

    private final String email;

    private final String imageUrl;

    @Size(max = 4000)
    private final String principal;

    private final SocialType socialType;

    private final Set<UserRole> roles;

    private final LocalDateTime createdDate;

    private final LocalDateTime updatedDate;
}
