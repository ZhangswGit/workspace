package com.ultrapower.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ultrapower.demo.Service.EhcacheManagerService;
import com.ultrapower.demo.Service.EhcacheService;
import com.ultrapower.demo.config.JwtCache;
import com.ultrapower.msa.sdk.jwt.JWTHelper;

@RestController
public class BusinessController {
	@Autowired
	private DemoFeignClient demoFeignClient;
	@Autowired
	private ProjectFeignClient projectFeignClient;
	@Autowired
	private UltraMsaFeignClient ultraMsaFeignClient;
	@Autowired
	private NexusFeignClient nexusFeignClient;
	@Autowired
	@Qualifier("JWTCacheServiceImpl")
	private EhcacheService<String> ehcacheService;
	@Autowired
	@Qualifier("JWTCacheManagerServiceImpl")
	private EhcacheManagerService<String> ehcacheManagerService;

	private String AK = "TSSO";

	@RequestMapping("/hello/{username}")
	@ResponseBody
	public String hello(@PathVariable("username") String username) {
		// 通过网关
		return ultraMsaFeignClient.hello(username);
	}

	@RequestMapping("/helloA/{username}")
	@ResponseBody
	public String helloA(@PathVariable("username") String username) {
		// 通过MSA获取访问 ultra-cmdb-b 服务的jwt
		String receiveJwt = JwtCache.getCache(AK, "ultra-cmdb-b");
		if (StringUtils.isBlank(receiveJwt)) {
			// receiveJwt = ultraMsaFeignClient.receiveJwt("ultra-cmdb-b");
			JWTHelper.createJWT("UCAS", 120 * 60 * 1000, "TSSO", "app", "TSSO", "TSSO",
					"02d70ea1-a0de-4f39-a5dd-2d0d95110870");
			JwtCache.putCache(AK, "ultra-cmdb-b", receiveJwt);
		}
		// 解析jwt
		String jwt = JSONObject.parseObject(receiveJwt).getString("message");
		// 获取 ultra-cmdb-b 接口数据
		
		return demoFeignClient.helloA(jwt, username);
	}

	@RequestMapping("/helloB/{username}")
	@ResponseBody
	public String helloB(@PathVariable("username") String username) {
		// 同一应用下服务调用
		return demoFeignClient.helloB(username);
	}

	@RequestMapping("/helloProject")
	@ResponseBody
	public String helloProject() {
		// 同一应用下服务调用
		projectFeignClient.helloA();
		return ehcacheManagerService.findByName("11111");
	}
	
	@RequestMapping("/helloNexus/{username}")
	@ResponseBody
	public String helloNexus(@PathVariable("username") String username) {
		// 同一应用下服务调用
		ehcacheManagerService.addPara("11111", "3333");
		nexusFeignClient.helloA(username);
		return ehcacheManagerService.findByName("11111");
	}

}
