package com.ultrapower.demo.Service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ultrapower.demo.Service.EhcacheService;


@Service("JWTCacheServiceImpl")
public class CodeCacheServiceImpl implements EhcacheService<String> {
	
	private static Logger logger = LoggerFactory.getLogger(CodeCacheServiceImpl.class);

	@Override
	@CachePut(value = "JWTCache#2", key = "#id")
	public String addPara(String id, String code) {
		logger.info("Ehcache存code,key:"+id+",code:"+code);
		return code;
	}

	@Override
	@Cacheable(value = "JWTCache#2", key = "#id")
	public String findByName(String id) {
		return null;
	}
	
	@Override
	@CacheEvict(value = "JWTCache", key = "#id", beforeInvocation=false)
	public void deletePara(String id) {
		logger.info("Ehcache删除code,key:"+id);
	}

}
