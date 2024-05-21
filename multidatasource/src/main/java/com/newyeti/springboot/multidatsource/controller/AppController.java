package com.newyeti.springboot.multidatasource.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AppController {
 
    @GetMapping("/test")
    public String test() {
        return "The application is working";
    }
    
}