// src/main/java/com/example/demo/config/DataInitializer.java
package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("test").isEmpty()) {
                User u = new User();
                u.setUsername("test");
                u.setPassword(encoder.encode("123456"));
                u.setRole("ROLE_USER");
                repo.save(u);
            }
        };
    }
}
