package com.example.oauth2jwt.user.entity;

import com.example.oauth2jwt.common.enums.UserRoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class UserRole {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRoleType name;

    @Builder
    public UserRole(Integer id, UserRoleType name) {
        this.id = id;
        this.name = name;
    }
}
