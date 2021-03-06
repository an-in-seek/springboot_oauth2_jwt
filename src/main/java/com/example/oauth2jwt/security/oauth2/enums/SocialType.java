package com.example.oauth2jwt.security.oauth2.enums;

public enum SocialType {
    LOCAL("local"),
    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao"),
    GITHUB("github");

    private final String ROLE_PREFIX = "ROLE_";
    private final String name;

    SocialType(String name) { this.name = name; }
    public String getRoleType() { return ROLE_PREFIX + name.toUpperCase(); }
    public String getValue() { return name; }
    public boolean isEquals(String authority) { return this.name.equals(authority); }
}