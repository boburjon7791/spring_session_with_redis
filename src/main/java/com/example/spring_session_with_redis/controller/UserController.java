package com.example.spring_session_with_redis.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final RedisTemplate<String, Object> redisTemplate;

    public UserController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public String helloWorld(){
        return "hello world 2";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        System.out.println("(redisTemplate.delete(request.getSession().getId())) = " + (redisTemplate.delete(request.getSession().getId())));
        return "success logout";
    }
}
