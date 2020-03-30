package com.ultrapower.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class ProjectServiceHystrix implements ProjectFeignClient {

	@Override
	public String helloA() {
		return "error";
	}
}
