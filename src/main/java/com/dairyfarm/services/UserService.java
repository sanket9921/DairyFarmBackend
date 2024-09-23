package com.dairyfarm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.models.User;
import com.dairyfarm.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User authenticate(String usernameOrEmail, String password) {
        User user = userRepository.findByUsername(usernameOrEmail).get();

        // If the user is not found by username, try to find by email
        if (user == null) {
            user = userRepository.findByEmail(usernameOrEmail);
        }

        // Check if user exists and password matches
        if (user != null && password.equals(user.getPassword())) {
            return user;  // Return the authenticated user
        }

        return null;  // Return null if authentication fails
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }
    public boolean usernameOrEmailExists(String username, String email) {
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
    }
}
