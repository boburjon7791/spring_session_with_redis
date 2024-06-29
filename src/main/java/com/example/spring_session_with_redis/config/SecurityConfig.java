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
                /**
                 * While any request will be send to the server, backend saves all session ids to the redis database.
                 */
                .formLogin(formLogin -> {
                    formLogin.defaultSuccessUrl("/");
                    formLogin.permitAll();
                })
                /**
                 * If a client sends logout request, client's session id will be removed from the redis database automatically.
                 */
                .logout(logoutConfig -> {
                    logoutConfig.permitAll();
                    logoutConfig.logoutSuccessUrl("/login");
                    logoutConfig.deleteCookies("JSESSIONID");
                })
                .rememberMe(rememberMeConfig -> {
                    rememberMeConfig.alwaysRemember(true);
                    rememberMeConfig.tokenValiditySeconds(60*60*24);
                })
                .build();
    }
}
