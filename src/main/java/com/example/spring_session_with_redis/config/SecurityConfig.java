package com.example.spring_session_with_redis.config;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Log log = LogFactory.getLog(SecurityConfig.class);
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
                .formLogin(loginConfig ->{
                    loginConfig.successForwardUrl("/api/users");
                    loginConfig.failureForwardUrl("/login");
                    loginConfig.permitAll();
                })
                .logout(
                logoutConfigurer -> logoutConfigurer.logoutSuccessHandler((request, response, authentication) -> {
                    logoutConfigurer.deleteCookies("JSESSIONID");
                    logoutConfigurer.logoutSuccessUrl("/login");
                    logoutConfigurer.permitAll();
                    String sessionId = request.getSession().getId();
                    redisTemplate.delete("spring:session:sessions:" + sessionId);
                    redisTemplate.delete("spring:session:sessions:expires:" + sessionId);
                })
        ).build();
    }
}
