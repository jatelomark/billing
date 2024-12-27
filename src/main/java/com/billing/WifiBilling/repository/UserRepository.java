package com.billing.WifiBilling.repository;

import com.billing.WifiBilling.model.User; // Import the User model
import org.springframework.data.jpa.repository.JpaRepository; // Import Spring Data JPA repository interface

import java.util.Optional; // Import Optional for nullable types

// Interface for user data access, extending JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Find a user by their email address
}
