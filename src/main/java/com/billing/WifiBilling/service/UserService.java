package com.billing.WifiBilling.service;

import com.billing.WifiBilling.dto.UserUpdateDto;
import com.billing.WifiBilling.model.User;
import com.billing.WifiBilling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(Long id, @Valid UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setUserName(userUpdateDto.getUserName());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword()); // Make sure to handle the password securely

        return userRepository.save(user);
    }
}
