package com.ultrapower.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ultrapower.demo.config.EhcacheUtil;

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

	public Cache<String, String> jwtCache = EhcacheUtil.getJwtCache();

	public static int n = 0;
	
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
		String receiveJwt = jwtCache.get(AK + "ultra-cmdb-b");
		if (StringUtils.isBlank(receiveJwt)) {
			 receiveJwt = ultraMsaFeignClient.receiveJwt("ultra-cmdb-b");
			jwtCache.put(AK + "ultra-cmdb-b", receiveJwt);
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
		return "";
	}

	@RequestMapping("/helloNexus/{username}")
	@ResponseBody
	public String helloNexus(@PathVariable("username") String username) {
		// 同一应用下服务调用
		jwtCache.put("11111", "3333");
		nexusFeignClient.helloA(username);
		return jwtCache.get("11111");
	}

	@RequestMapping("/testchcahe/key/{key}/value/{value}")
	@ResponseBody
	public String chcahe(@PathVariable("key") String key, @PathVariable("value") String value) throws RuntimeException {
		// 存入第1条数据
//		jwtCache.put(key, value);
		// 取出并输出
		n++;
		System.out.println("----------");
		System.out.println(n);
		return value;
	}
	
	@RequestMapping("/testchcahe")
	@ResponseBody
	public String chcahe1() {
//		int a = Integer.valueOf("aaa");
		n++;
		System.out.println(n);
		if(n % 3 != 0 ){
			try {
				Thread.sleep(7000l);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "cache1";
	}

	@RequestMapping("/getchcahe/key/{key}")
	@ResponseBody
	public String getchcahe(@PathVariable("key") String key) {
		return jwtCache.get(key);
	}

}
