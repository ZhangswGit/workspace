package com.ultrapower.demo.config;

import java.time.Duration;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class EhcacheUtil {
	private static final Logger logger = LoggerFactory.getLogger(EhcacheUtil.class);

	private static CacheManager cacheManager;

	/**
	 * 获取JWT缓存实例
	 * 
	 * @return
	 */
	public static Cache<String, String> getJwtCache() {
		if (cacheManager == null) {
			return null;
		}
		return cacheManager.getCache("jwtcache", String.class, String.class);
	}

	/**
	 * 根据缓存名字获取缓存实例
	 * 
	 * @param cacheName
	 *            缓存名字
	 * @return
	 */
	public static Cache<String, Object> getCache(String cacheName) {
		if (cacheManager == null) {
			return null;
		}
		return cacheManager.getCache(cacheName, String.class, Object.class);
	}

	/**
	 * 提价缓存实例
	 * 
	 * @param cacheName
	 *            缓存名字
	 * @param expired
	 *            过期时间
	 * @return
	 */
	public static Cache<String, Object> addCache(String cacheName, long expired) {
		if (cacheManager == null) {
			return null;
		}
		if (getCache(cacheName) != null) {
			return getCache(cacheName);
		}
		CacheConfiguration<String, Object> cacheConfiguration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(1000L))
				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(expired))).build();
		Cache<String, Object> createCache = cacheManager.createCache(cacheName, cacheConfiguration);
		return createCache;
	}

	/**
	 * 初始化Ehcache缓存对象
	 */
	public EhcacheUtil() {
		logger.info("[Ehcache配置初始化<开始>]");
		// 配置默认缓存属性
		CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
				// 缓存数据K和V的数值类型
				// 在ehcache3.3中必须指定缓存键值类型,如果使用中类型与配置的不同,会报类转换异常
				String.class, String.class,
				ResourcePoolsBuilder.newResourcePoolsBuilder()
						// 设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
						.heap(1000L, EntryUnit.ENTRIES)
						// 设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
						.offheap(100L, MemoryUnit.MB)
						// 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
						.disk(500L, MemoryUnit.MB, false))
				.build();
		// CacheManager管理缓存
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				// 持久化地址
				.with(CacheManagerBuilder.persistence(Class.class.getClass().getResource("/").getPath()))
				// 设置一个默认缓存配置
				.withCache("defaultCache", cacheConfiguration)
				// 设置jwt缓存配置
				.withCache("jwtcache", CacheConfigurationBuilder
						.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(1000L))
						.withExpiry(
								ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(Constant.cahceExpired_in)))
						.build())
				// 创建之后立即初始化
				.build(true);
		logger.info("[Ehcache配置初始化<完成>]");
	}
}
