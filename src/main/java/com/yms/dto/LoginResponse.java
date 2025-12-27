package com.yms.dto;

import com.yms.entity.Role;

public class LoginResponse {

    private String message;
    private Role role;
    private String token;

    public LoginResponse(String message, Role role, String token) {
        this.message = message;
        this.role = role;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public Role getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
