 package com.telmi.app.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.telmi.app.dto.ApiResponse;
import com.telmi.app.dto.auth.LoginRequest;
import com.telmi.app.dto.auth.RegisterRequest;
import com.telmi.app.entity.User;
import com.telmi.app.repository.UserRepository;
import com.telmi.app.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER → return saved User
    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    // ✅ LOGIN → return JWT token + user
    public ApiResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return new ApiResponse(false, "Invalid Credentials");
        }

        User user = userOpt.get();

        boolean match = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!match) {
            return new ApiResponse(false, "Invalid Credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        // Return both token and user
        return new ApiResponse(true, "Login successful", token, user);
    }

    // Generate token for a user
    public String generateToken(User user) {
        return jwtUtil.generateToken(user.getEmail());
    }
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
     
    }

