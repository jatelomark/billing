package com.billing.WifiBilling.service;

import com.billing.WifiBilling.model.User;

import java.util.List;

public interface UserService {
    // Add a new user
    User saveUser (User user);

    // Get all users
    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void deleteUserById(Long id);

    void deleteUserByEmail(String email);

    //Count the total number of Users
    long getTotalUsers();
}
