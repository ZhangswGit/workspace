package com.ultrapower.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class DemoServiceHystrix implements DemoFeignClient {

	@Override
	public String helloA(String authorization, String username) {
		return "error";
	}

	@Override
	public String helloB(String username) {
		return "error";
	}
}
