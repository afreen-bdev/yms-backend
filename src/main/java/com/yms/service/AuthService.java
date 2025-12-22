package com.yms.service;

import com.yms.dto.LoginRequest;
import com.yms.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
