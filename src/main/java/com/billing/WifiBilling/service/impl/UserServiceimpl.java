package com.billing.WifiBilling.service.impl;

import com.billing.WifiBilling.model.User;
import com.billing.WifiBilling.repository.UserRepository;
import com.billing.WifiBilling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    @Override
    public long getTotalUsers(){
        return userRepository.count();
    }
}
