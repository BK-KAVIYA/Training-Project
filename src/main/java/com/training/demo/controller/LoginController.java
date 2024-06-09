package com.training.demo.controller;

import com.training.demo.model.Login;
import com.training.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public Login registerLogin(@RequestBody Login login) {
        return loginService.save(login);
    }

    @GetMapping("/{username}")
    public Login getLogin(@PathVariable String username) {
        return loginService.findByUsername(username).orElse(null);
    }
}

