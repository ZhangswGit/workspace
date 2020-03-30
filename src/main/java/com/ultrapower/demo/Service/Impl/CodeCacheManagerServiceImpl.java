package com.ultrapower.demo.Service.Impl;

import javax.annotation.Resource;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ultrapower.demo.Service.EhcacheManagerService;

@Service("JWTCacheManagerServiceImpl")
public class CodeCacheManagerServiceImpl implements EhcacheManagerService<String> {

	@Resource
	private CacheManager cacheManager;
	
	Cache<String, String> cache = cacheManager.getCache("JWTCACHE", String.class, String.class);

	private static Logger logger = LoggerFactory.getLogger(CodeCacheManagerServiceImpl.class);

	@Override
	public String addPara(String id, String t) {
		cache.put(id, t);
		return t;
	}

	@Override
	public String findByName(String id) {
		return cache.get(id);
	}

}
