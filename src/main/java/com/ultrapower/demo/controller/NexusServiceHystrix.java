package com.ultrapower.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class NexusServiceHystrix implements NexusFeignClient {

	@Override
	public String helloA(String repoId) {
		return "error";
	}
}
