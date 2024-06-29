package com.example.spring_session_with_redis.config;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpSessionConfig {
    @Bean
    public HttpSessionListener httpSessionListener(){
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                HttpSession session = se.getSession();
                session.setMaxInactiveInterval(60);
            }
        };
    }
}
