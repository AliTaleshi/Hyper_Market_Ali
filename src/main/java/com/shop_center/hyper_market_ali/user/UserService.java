package com.shop_center.hyper_market_ali.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> registerUser(User newUser) {
        ResponseEntity<String> validationResponse = validateUserFields(newUser);
        if (validationResponse != null) {
            return validationResponse;
        }

        if (isUsernameTaken(newUser.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }
        if (isEmailTaken(newUser.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }

        String hashedPassword = hashPassword(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        User createdUser = userRepository.save(newUser);

        if (createdUser != null) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }

    private ResponseEntity<String> validateUserFields(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Username, email, and password are required.");
        }
        if (!isEmailTaken(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format.");
        }

        return null; // No validation issues
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

}
