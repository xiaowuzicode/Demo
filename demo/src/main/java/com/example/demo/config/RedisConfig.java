package com.example.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
	
	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate){
		RedisCacheManager rcm=new RedisCacheManager(redisTemplate);
		return rcm;
	}
	
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
		
		
		return null;
		
	}
		
	

}
