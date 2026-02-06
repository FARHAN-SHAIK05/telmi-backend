 package com.telmi.app.controller;

import com.telmi.app.entity.User;
import com.telmi.app.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public User getProfile() {
        return userService.getCurrentUser();
    }
}
