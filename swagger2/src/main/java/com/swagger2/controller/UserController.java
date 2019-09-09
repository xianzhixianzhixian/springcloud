package com.swagger2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/user")
public class UserController {

    @PostMapping(value = "/login")
    public String login() {
        return "login";
    }
}
