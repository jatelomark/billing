package com.billing.WifiBilling.controller;

import com.billing.WifiBilling.dto.UserCreateDto; // Import DTO for user creation
import com.billing.WifiBilling.dto.UserResponseDto; // Import DTO for user responses
import com.billing.WifiBilling.model.User; // Import User model
import com.billing.WifiBilling.service.UserService; // Import UserService for business logic
import org.springframework.beans.factory.annotation.Autowired; // Import Spring's Autowired annotation
import org.springframework.data.domain.Page; // Import Page interface for pagination
import org.springframework.data.domain.Pageable; // Import Pageable interface for pagination requests
import org.springframework.http.HttpStatus; // Import HttpStatus for response status
import org.springframework.http.ResponseEntity; // Import ResponseEntity for standard responses
import org.springframework.web.bind.annotation.*; // Import Spring Web annotations
import javax.validation.Valid; // Import for validation annotations

@RestController // Mark this class as a REST controller
@RequestMapping("/api/users") // Base URL for user-related APIs
public class UserController {

    private final UserService userService; // Dependency injection of UserService

    @Autowired // Automatically inject the UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        User newUser = userService.saveUser(userCreateDto); // Create a new user using the service
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponseDto(newUser)); // Return created response
    }

    // Endpoint to get all users with pagination
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable); // Retrieve users with pagination
        Page<UserResponseDto> response = users.map(this::convertToResponseDto); // Map users to response DTOs
        return ResponseEntity.ok(response); // Return response with users
    }

    // Endpoint to get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id); // Retrieve the user by ID
        return ResponseEntity.ok(convertToResponseDto(user)); // Return the user response
    }

    // Endpoint to update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UserCreateDto userCreateDto) {
        User updatedUser = userService.updateUser(id, userCreateDto); // Update the user
        return ResponseEntity.ok(convertToResponseDto(updatedUser)); // Return updated user response
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id); // Delete the user
        return ResponseEntity.ok("User deleted successfully"); // Return success message
    }

    // Helper method to convert User entity to UserResponseDto
    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto responseDto = new UserResponseDto(); // Create new response DTO
        responseDto.setId(user.getId()); // Set user ID
        responseDto.setUserName(user.getUserName()); // Set user name
        responseDto.setEmail(user.getEmail()); // Set user email
        return responseDto; // Return the response DTO
    }
}
