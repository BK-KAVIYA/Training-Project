package com.training.demo.controller;


import com.training.demo.model.User;
import com.training.demo.model.UserDTO;
import com.training.demo.security.JwtTokenProvider;
import com.training.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/auth")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if the username already exists
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        userService.save(user);

        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = generateToken(user.getUsername(), user.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + token); // Adding token to the response header

        Map<String, Object> response = new HashMap<>();
        response.put("role", userDetails.getAuthorities());

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    private String generateToken(String username, String password) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return jwtTokenProvider.createToken(authentication);
    }

    @GetMapping("/{username}")
    public User getLogin(@PathVariable String username) {
        return userService.findByUsername(username).orElse(null);
    }

    @GetMapping("get-by/{id}")
    public UserDTO getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setContactNumber(user.getContactNumber());
        userDTO.setRole(user.getRole());


        return userDTO;
    }


}



