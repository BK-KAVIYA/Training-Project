package com.training.demo.service;


import com.training.demo.model.Login;
import com.training.demo.repository.LoginRepository;
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
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    public Optional<Login> findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    // Method to check if a user with the given username already exists
    public boolean existsByUsername(String username) {
        Optional<Login> existingUser = loginRepository.findByUsername(username);
        return existingUser.isPresent();
    }

    public Login save(Login login) {
        login.setPassword(passwordEncoder().encode(login.getPassword()));
        return loginRepository.save(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(login);
    }
}

