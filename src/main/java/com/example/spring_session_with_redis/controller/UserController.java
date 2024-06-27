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
    private final RedisTemplate<String, String> redisTemplate;

    public UserController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        System.out.println("sessionId = " + sessionId);
        redisTemplate.opsForValue().set("session:"+ sessionId, sessionId);
        return "redirect:/api/users";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        System.out.println("sessionId = " + sessionId);
        Boolean delete = redisTemplate.delete("session:" + sessionId);
        System.out.println("delete = " + delete);
        return "redirect:/login";
    }

    @GetMapping
    public String helloWorld(){
        return "hello world 2";
    }
}
