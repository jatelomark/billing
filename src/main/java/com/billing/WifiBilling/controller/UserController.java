package com.billing.WifiBilling.controller;

import com.billing.WifiBilling.model.User;
import com.billing.WifiBilling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") //base endpoint for user-related operations
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user){
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalUsers(){
        long count = userService.getTotalUsers();
        return ResponseEntity.ok(count);
    }
}