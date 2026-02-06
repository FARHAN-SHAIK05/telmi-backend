 package com.telmi.app.dto;

import com.telmi.app.entity.User;

public class ApiResponse {
    private boolean success;
    private String message;
    private String token;
    private User user;  // use User instead of Object for type safety

    // Default constructor (needed for serialization)
    public ApiResponse() {}

    // Constructor for simple responses
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Constructor for responses with token + user
    public ApiResponse(boolean success, String message, String token, User user) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.user = user;
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getToken() { return token; }
    public User getUser() { return user; }

    // Setters
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setToken(String token) { this.token = token; }
    public void setUser(User user) { this.user = user; }
}