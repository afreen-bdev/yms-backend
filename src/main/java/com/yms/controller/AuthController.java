package com.yms.controller;

import org.springframework.web.bind.annotation.*;

import com.yms.dto.LoginRequest;
import com.yms.dto.LoginResponse;
import com.yms.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
