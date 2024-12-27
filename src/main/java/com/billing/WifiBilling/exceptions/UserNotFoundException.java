package com.billing.WifiBilling.exception;

// Custom exception class for user not found scenarios
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message); // Call parent (RuntimeException) constructor
    }
}
