package com.example.oauth2jwt.user.entity;

import com.example.oauth2jwt.common.entity.BaseDateTimeEntity;
import com.example.oauth2jwt.security.oauth2.enums.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseDateTimeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String imageUrl;

    @Column
    private String principal;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<UserRole> roles;

    @Builder
    public User(Long id, String username, String password, String email, String imageUrl, String principal, SocialType socialType, Set<UserRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.principal = principal;
        this.socialType = socialType;
        this.roles = roles;
    }
}
