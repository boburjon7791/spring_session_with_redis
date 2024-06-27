package com.example.spring_session_with_redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(@Lazy RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(httpRequests->{
                    httpRequests.anyRequest().authenticated();
                })
                .logout(
                logoutConfigurer -> logoutConfigurer.logoutSuccessHandler((request, response, authentication) -> {
                    logoutConfigurer.deleteCookies("JSESSIONID");
                    logoutConfigurer.logoutSuccessUrl("/login");
                    String sessionId = request.getSession().getId();
                    redisTemplate.delete("spring:session:sessions:" + sessionId);
                    redisTemplate.delete("spring:session:sessions:expires:" + sessionId);
                })
        ).build();
    }
}
