package com.billing.WifiBilling.model;

import jakarta.persistence.*; // Import JPA annotations for Entity management
import lombok.Data; // Import Lombok for automatic getters and setters

@Entity // Mark this class as a JPA entity
@Table(name = "users") // Specify the table name in the database
@Data // Generate getters, setters, and other utility methods automatically
public class User {
    @Id // Mark this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID value
    private Long id; // Unique identifier for the user

    @Column(nullable = false) // Specifies that this column cannot be null
    private String userName; // The user's name

    @Column(nullable = false, unique = true) // Specifies that this column must be unique
    private String email; // The user's email

    @Column(nullable = false) // Specifies that this column cannot be null
    private String password; // The user's password, ensure secure handling
}
