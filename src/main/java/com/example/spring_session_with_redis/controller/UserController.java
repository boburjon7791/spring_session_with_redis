package com.example.spring_session_with_redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * login and logout APIs for testing spring session with postman for rest APIs
* */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public String helloWorld(){
        return "hello world 10";
    }
}
