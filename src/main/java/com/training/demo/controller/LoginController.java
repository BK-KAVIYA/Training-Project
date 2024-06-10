package com.training.demo.controller;


import com.training.demo.model.Login;
import com.training.demo.security.JwtTokenProvider;
import com.training.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Login login) {
        // Check if the username already exists
        if (loginService.existsByUsername(login.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        // Save the login details
        loginService.save(login);

        // Generate JWT token
        String token = generateToken(login.getUsername(), login.getPassword());

        // Return the token as response
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {
        System.out.println("call");
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );

        // Set the authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = generateToken(login.getUsername(), login.getPassword());

        // Return the token as response
        return ResponseEntity.ok(token);
    }

    private String generateToken(String username, String password) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Generate JWT token
        return jwtTokenProvider.createToken(authentication);
    }

    @GetMapping("/{username}")
    public Login getLogin(@PathVariable String username) {
        return loginService.findByUsername(username).orElse(null);
    }
}



