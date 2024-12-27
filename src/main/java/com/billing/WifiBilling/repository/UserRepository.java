package com.billing.WifiBilling.dto;

import lombok.Data; // Import Lombok for automatic getters and setters

@Data // Generate getters, setters, and other utility methods automatically
public class UserResponseDto {
    private Long id; // Unique identifier of the user
    private String userName; // User's name
    private String email; // User's email address
}
