package com.example.spring_session_with_redis;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class SpringSessionWithRedisApplication {
	private final StringRedisTemplate stringRedisTemplate;

    public SpringSessionWithRedisApplication(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringSessionWithRedisApplication.class, args);
	}
	@GetMapping
	public String index() {
        return "hello";
    }
	@Scheduled(cron = "0 0 1 * * *")
	public void delete(){
		String script = "local keys = redis.call('KEYS', '*') " +
				"for i, key in ipairs(keys) do " +
				"redis.call('EXPIRE', key, 1) " +
				"end " +
				"return keys";

		DefaultRedisScript<List<String>> redisScript = new DefaultRedisScript<>();
		redisScript.setScriptText(script);

		stringRedisTemplate.execute(redisScript, Collections.emptyList());
	}

}
