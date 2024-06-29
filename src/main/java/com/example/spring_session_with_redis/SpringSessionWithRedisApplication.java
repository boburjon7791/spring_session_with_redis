package com.example.spring_session_with_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class SpringSessionWithRedisApplication {
	private final CacheManager cacheManager;
    public SpringSessionWithRedisApplication(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringSessionWithRedisApplication.class, args);
	}
	@GetMapping
	public String index() {
        return "hello";
    }
	@Scheduled(cron = "0 * * * * *")
	public void init(){
		cacheManager.getCacheNames().forEach(session -> {
			Objects.requireNonNull(cacheManager.getCache(session)).evictIfPresent(session);
			System.out.println("session = " + session);
		});
	}
}
