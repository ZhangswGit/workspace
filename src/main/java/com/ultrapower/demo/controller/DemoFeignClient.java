package com.ultrapower.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ultra-cmdb-b", url = "${service.url}", fallback = DemoServiceHystrix.class)
public interface DemoFeignClient {
	/**
	 * 跨应用之间调用
	 * @param authorization jwt需要调用MSA申请票据接口获取
	 * @param username 接口参数
	 * @return
	 */
	@RequestMapping("/ultra-cmdb-b/service/v1/user/{username}")
	public String helloA(@RequestHeader("Authorization") String authorization, @RequestParam("username") String username);

	/**
	 * 同应用之间调用
	 * @param username 接口参数
	 * @return
	 */
	@RequestMapping("/ultra-cmdb-b/service/v1/user/{username}")
	public String helloB(@RequestParam("username") String username);
}
