package com.ultrapower.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ultrapower.demo.config.FeignInterceptor;

@FeignClient(name = "ultra-msa-gateway", fallback = UltraMsaServiceHystrix.class)
public interface UltraMsaFeignClient {
	/**
	 * 测试接口
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/ultra-cmdb-b/service/v1/user/{username}")
	public String hello(@RequestParam("username") String username);

	/***
	 * 获取票据接口
	 * @param providerAK 被访问的服务名
	 * @return
	 */
	@RequestMapping("/ultra-msa-sso/v1/auth/application/token/providerAK/{providerAK}")
	public String receiveJwt(@RequestParam("providerAK") String providerAK);
}
