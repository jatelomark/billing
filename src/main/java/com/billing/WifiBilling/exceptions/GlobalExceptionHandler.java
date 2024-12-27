package com.billing.WifiBilling.exception;

import org.springframework.http.HttpStatus; // Import HttpStatus for response status
import org.springframework.http.ResponseEntity; // Import ResponseEntity for standard responses
import org.springframework.web.bind.MethodArgumentNotValidException; // Import for handling validation exceptions
import org.springframework.web.bind.annotation.ControllerAdvice; // Import for global exception handling
import org.springframework.web.bind.annotation.ExceptionHandler; // Import for handling specific exceptions

import java.util.HashMap; // Import HashMap for error details
import java.util.Map; // Import Map for error details

@ControllerAdvice // Mark this class as a global exception handler
public class GlobalExceptionHandler {

    // Handle UserNotFoundException globally
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // Return not found error message
    }

    // Handle validation errors globally
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>(); // Create a map to hold error messages
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())); // Fill map with field errors
        return ResponseEntity.badRequest().body(errors); // Return bad request with errors
    }
    
    // Other exception handlers can be added as needed...
}
