package com.example.spring_session_with_redis.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * login and logout APIs for testing spring session with postman for rest APIs
* */
@RestController
@RequestMapping("/api/users")
public class UserController {
    // this is for custom configuration
    private final RedisTemplate<String, String> redisTemplate;

    public UserController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public String helloWorld(){
        return "hello world 2";
    }
}
