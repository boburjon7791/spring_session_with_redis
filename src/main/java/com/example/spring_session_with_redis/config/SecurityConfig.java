package com.example.spring_session_with_redis.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Log log = LogFactory.getLog(SecurityConfig.class);
    private final RedisTemplate<String, Object> redisTemplate;

    public SecurityConfig(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(httpRequests->{
                    httpRequests.requestMatchers("/login").permitAll();
                    httpRequests.anyRequest().authenticated();
                })
                .build();
    }
    @Bean
    public OncePerRequestFilter oncePerRequestFilter() {
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                if (request.getRequestURI().equals("/logout")) {
                    redisTemplate.delete("spring:session:sessions:"+request.getSession().getId());
                }
                filterChain.doFilter(request, response);
            }
        };
    }
}
