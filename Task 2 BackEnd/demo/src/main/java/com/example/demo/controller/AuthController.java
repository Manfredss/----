// src/main/java/com/example/demo/controller/AuthController.java
package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {
    private final AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> creds, HttpServletRequest req) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(creds.get("username"), creds.get("password"));
        try {
            Authentication auth = authManager.authenticate(token);
            // On success, Spring stores the Authentication into the session automatically
            return ResponseEntity.ok().build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
