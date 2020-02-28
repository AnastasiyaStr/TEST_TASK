package com.javainuse.nastia.controller;

import com.javainuse.nastia.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping({"/employees"})
public class SecurityController {
    @GetMapping(produces = "application/json")
    @RequestMapping({"/validateLogin"})
    public User validateLogin() {
        return new User("User successfully authenticated");
    }
}
