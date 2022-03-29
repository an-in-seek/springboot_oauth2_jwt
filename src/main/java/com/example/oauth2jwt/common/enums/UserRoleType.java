package com.example.oauth2jwt.common.enums;

public enum UserRoleType {
    ROLE_USER("USER"),
    ROLE_MODERATOR("MODERATOR"),
    ROLE_ADMIN("ADMIN");

    private String value;
    private final String ROLE_PREFIX = "ROLE_";

    UserRoleType(String value) {
        this.value = value;
    }

    public String getRoleType() { return ROLE_PREFIX + value.toUpperCase(); }

    public boolean isEquals(String name) {
        return this.toString().equals(name);
    }
}
