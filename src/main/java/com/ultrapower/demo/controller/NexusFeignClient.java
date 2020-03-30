package com.ultrapower.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "khan-nexus", fallback = NexusServiceHystrix.class)
public interface NexusFeignClient {
	@RequestMapping("/khan-nexus/nexus/repo/{repoId}")
	public String helloA(@RequestParam("repoId") String repoId);
}
