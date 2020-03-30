package com.ultrapower.demo.config;

import java.time.Duration;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	/**
	 * 传递数据的超时时间
	 */
	private int readTimeout = 3000;
	/**
	 * 建立连接的超时时间
	 */
	private int connectTimeout = 3000;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	@Bean("restTemplateLoadBalanced")
	@LoadBalanced
	public RestTemplate restTemplateLoadBalanced() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(readTimeout);
		factory.setConnectTimeout(connectTimeout);
		return factory;
	}

//	fd
	// @Bean
	// public RedisCacheManager cacheManager(RedisConnectionFactory
	// connectionFactory) {
	// RedisCacheConfiguration config =
	// RedisCacheConfiguration.defaultCacheConfig()
	// .entryTtl(Duration.ofMinutes((long) (1))); // 60s缓存失效
	// RedisCacheManager redisCacheManager = RedisCacheManager
	// .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
	// .cacheDefaults(config).build();
	// return redisCacheManager;
	// }
}
