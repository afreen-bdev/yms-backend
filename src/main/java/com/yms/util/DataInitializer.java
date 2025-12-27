package com.yms.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yms.entity.Role;
import com.yms.entity.User;
import com.yms.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {

            // ADMIN
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ROLE_ADMIN);
                admin.setActive(true);
                userRepo.save(admin);
            }

            // GATE
            if (userRepo.findByUsername("gate1").isEmpty()) {
                User gate = new User();
                gate.setUsername("gate1");
                gate.setPassword(encoder.encode("gate123"));
                gate.setRole(Role.ROLE_GATE);
                gate.setActive(true);
                userRepo.save(gate);
            }

            // YARD
            if (userRepo.findByUsername("yard1").isEmpty()) {
                User yard = new User();
                yard.setUsername("yard1");
                yard.setPassword(encoder.encode("yard123"));
                yard.setRole(Role.ROLE_YARD);
                yard.setActive(true);
                userRepo.save(yard);
            }
        };
    }
}