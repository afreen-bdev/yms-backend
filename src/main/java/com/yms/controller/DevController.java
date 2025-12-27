package com.yms.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yms.entity.Role;
import com.yms.entity.User;
import com.yms.repository.UserRepository;

@RestController
@RequestMapping("/api/dev")
public class DevController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DevController(UserRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/seed-admin")
    public String seedAdmin() {

        if (userRepository.findByUsername("admin").isPresent()) {
            return "Admin already exists";
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ROLE_ADMIN);

        userRepository.save(admin);
        return "Admin created successfully";
    }
}
