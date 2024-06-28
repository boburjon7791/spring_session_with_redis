package com.example.spring_session_with_redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(httpRequests->{
                    httpRequests.requestMatchers("/login").permitAll();
                    httpRequests.anyRequest().authenticated();
                })
                .formLogin(formLogin -> {
                    formLogin.defaultSuccessUrl("/");
                    formLogin.permitAll();
                    formLogin.failureForwardUrl("/");
                })
                .build();
    }
}
