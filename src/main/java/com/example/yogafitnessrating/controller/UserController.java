package com.example.yogafitnessrating.controller;

import com.example.yogafitnessrating.service.AuthService;
import com.example.yogafitnessrating.dto.LoginRequest;
import com.example.yogafitnessrating.dto.AuthResponse;
import com.example.yogafitnessrating.dto.RegisterRequest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthService authService;
    private static final Set<String> tokenBlacklist = new HashSet<>();

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String tokenHeader) {
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token header");
        }

        String token = tokenHeader.substring(7); 
        tokenBlacklist.add(token);
        return ResponseEntity.ok("Logged out successfully");
    }

    public static boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }
}
