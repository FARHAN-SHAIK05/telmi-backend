 package com.telmi.app.controller;

import com.telmi.app.dto.ApiResponse;
import com.telmi.app.dto.auth.LoginRequest;
import com.telmi.app.dto.auth.RegisterRequest;
import com.telmi.app.entity.User;
import com.telmi.app.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER API
    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {
        try {
            // Save user and generate token
            User user = authService.register(request);
            String token = authService.generateToken(user);

            // Return token + user object so frontend can consume both
            return new ApiResponse(true, "Registration successful", token, user);
        } catch (IllegalArgumentException e) {
            // Validation errors (duplicate email, password mismatch)
            return new ApiResponse(false, e.getMessage());
        } catch (Exception e) {
            // Other errors (DB issues, etc.)
            return new ApiResponse(false, "Registration failed: " + e.getMessage());
        }
    }

    // LOGIN API (JWT)
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {
        // ✅ AuthService.login() now returns ApiResponse directly
        return authService.login(request);
    }
    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return authService.getByEmail(email);
    }

}