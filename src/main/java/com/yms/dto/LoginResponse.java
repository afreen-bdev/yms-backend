package com.yms.dto;

import com.yms.entity.Role;

public class LoginResponse {

    private String message;
    private Role role;

    public LoginResponse(String message, Role role) {
        this.message = message;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public Role getRole() {
        return role;
    }
}
