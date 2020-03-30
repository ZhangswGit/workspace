package com.ultrapower.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class UltraMsaServiceHystrix implements UltraMsaFeignClient {

	@Override
	public String receiveJwt(String service) {
		return "error";
	}

	@Override
	public String hello(String username) {
		return "error";
	}
}
