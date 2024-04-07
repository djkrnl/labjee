package com.example.labjee.services;

import com.example.labjee.models.User;
import com.example.labjee.repositories.UserRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void createOrUpdate(User user, boolean newPassword) {
        if (newPassword) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        userRepository.save(user);
    }
    
    public User getByUsername(String username) {
        if (userRepository.existsById(username)) {
            return userRepository.findById(username).get();
        }
        
        return null;
    }
    
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public int delete(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
            
            return 0;
        }
        
        return 1;
    }
}
