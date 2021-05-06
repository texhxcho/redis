package com.example.redistest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class RedistestApplication {

	static StringRedisTemplate redisTemplate;

	@Autowired
	public void setStringRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedistestApplication.class, args);

		final String key = "happyCommTest";
		final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
		stringStringValueOperations.set(key, "1"); // redis set 명령어

		for (int i = 0; i < 10000; i++) {
			final String result_1 = stringStringValueOperations.get(key); // redis get 명령어
			System.out.println("조회결과:" + result_1);
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
			}
		}
	}

}
