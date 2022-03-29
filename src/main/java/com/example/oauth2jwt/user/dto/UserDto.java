package com.example.oauth2jwt.user.dto;

import com.example.oauth2jwt.common.enums.SocialType;
import com.example.oauth2jwt.common.enums.UserRoleType;
import com.example.oauth2jwt.user.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
public class UserDto {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String profileImage;

    @Size(max = 4000)
    private String principal;

    private SocialType socialType;

    private Set<UserRole> roles;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
