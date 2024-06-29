package com.example.spring_session_with_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
@SpringBootApplication
public class SpringSessionWithRedisApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSessionWithRedisApplication.class, args);
	}
	@GetMapping
	public String index() {
        return "hello";
    }
}
