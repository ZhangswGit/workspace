package com.ultrapower.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "project", fallback = ProjectServiceHystrix.class)
public interface ProjectFeignClient {
	@RequestMapping("/project/v1/demandTree/demandTree")
	public String helloA();
}
