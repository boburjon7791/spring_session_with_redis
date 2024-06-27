package com.example.spring_session_with_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RestController
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
