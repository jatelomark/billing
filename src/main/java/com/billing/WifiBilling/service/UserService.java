package com.billing.WifiBilling.service;

import com.billing.WifiBilling.dto.UserCreateDto; // Import DTO for user creation
import com.billing.WifiBilling.exception.UserNotFoundException; // Import custom exception
import com.billing.WifiBilling.model.User; // Import User model
import com.billing.WifiBilling.repository.UserRepository; // Import User repository
import org.springframework.beans.factory.annotation.Autowired; // Import Spring's Autowired annotation
import org.springframework.data.domain.Page; // Import Page interface for pagination
import org.springframework.data.domain.Pageable; // Import Pageable interface for pagination requests
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Import for password encoding
import org.springframework.stereotype.Service; // Import Service annotation for service layer
import org.slf4j.Logger; // Import SLF4J Logger
import org.slf4j.LoggerFactory; // Import LoggerFactory for creating logger instances

@Service // Mark this class as a service component
public class UserService {

    private final UserRepository userRepository; // Dependency injection of UserRepository
    private final BCryptPasswordEncoder passwordEncoder; // For encoding passwords
    private static final Logger logger = LoggerFactory.getLogger(UserService.class); // Logger instance

    @Autowired // Automatically inject the UserRepository and PasswordEncoder
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to save a new user
    public User saveUser(UserCreateDto userCreateDto) {
        User user = new User(); // Create a new User object
        user.setUserName(userCreateDto.getUserName()); // Set userName from DTO
        user.setEmail(userCreateDto.getEmail()); // Set email from DTO
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword())); // Encode password securely

        logger.info("Creating user: {}", user.getEmail()); // Log the creation action
        return userRepository.save(user); // Save the user to the database
    }

    // Method to retrieve all users with pagination
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable); // Return paginated users
    }

    // Method to retrieve a user by their ID
    public User getUserById(Long id) {
        return userRepository.findById(id) // Attempt to find the user by ID
                .orElseThrow(() -> { // If not found, throw exception
                    logger.error("User not found with id: {}", id); // Log the error
                    return new UserNotFoundException("User not found with id: " + id); // Custom exception
                });
    }

    // Method to update an existing user's information
    public User updateUser(Long id, UserCreateDto userCreateDto) {
        User existingUser = getUserById(id); // Get existing user

        // Update user fields
        existingUser.setUserName(userCreateDto.getUserName());
        existingUser.setEmail(userCreateDto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userCreateDto.getPassword())); // Encode new password

        logger.info("Updating user: {}", existingUser.getEmail()); // Log the update action
        return userRepository.save(existingUser); // Save changes to the database
    }

    // Method to delete a user by their ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id); // Delete the user from the database
    }
}
