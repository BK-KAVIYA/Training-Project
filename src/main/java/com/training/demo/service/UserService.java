package com.training.demo.service;


import com.training.demo.model.User;
import com.training.demo.repository.UserRepository;
import com.training.demo.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to check if a user with the given username already exists
    public boolean existsByUsername(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        return existingUser.isPresent();
    }

    public User save(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}

