package com.shop_center.hyper_market_ali.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> registerUser(User newUser) {
        // Validate user input fields
        ResponseEntity<String> validationResponse = validateUserFields(newUser);
        if (validationResponse != null) {
            return validationResponse;
        }

        // Check if username or email is already taken
        if (isUsernameTaken(newUser.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }
        if (isEmailTaken(newUser.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }

        // Perform any additional steps (e.g., password hashing, email verification)
        // before saving the user to the repository
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

        // Perform additional validation if needed
        if (!isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format.");
        }

        return null; // No validation issues
    }

    // Other methods...
}
