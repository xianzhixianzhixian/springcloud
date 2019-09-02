package com.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${name:config-client}")
    private String name;

    @RequestMapping("/name")
    private String name() {
        return name;
    }
}
