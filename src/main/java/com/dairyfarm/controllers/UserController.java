package com.dairyfarm.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.dto.LoginResponse;
import com.dairyfarm.dto.UserCredentials;
import com.dairyfarm.models.User;
import com.dairyfarm.services.UserService;

@RestController
@CrossOrigin(origins = "*") // Allow all origins (all IPs)
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody UserCredentials credentials) {
        User user = userService.authenticate(credentials.getUsername(), credentials.getPassword());
        if (user != null) {
            LoginResponse response = new LoginResponse(user.getUsername(), user.getRole());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    
    @GetMapping("/check-username-email")
    public ResponseEntity<Boolean> checkUsernameOrEmailExists(@RequestParam String username, @RequestParam String email) {
        boolean exists = userService.usernameOrEmailExists(username, email);
        return ResponseEntity.ok(exists);
    }
}
