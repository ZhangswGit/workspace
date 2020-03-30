package com.ultrapower.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Value("${spring.application.name}")
	public String service;

	@GetMapping("/main")
	public String go(HttpServletRequest request, Model model) {
		model.addAttribute("account", "欢迎进入" + service + "服务，这是TSSO登录成功的首页地址 ");
		return "main";
	}
	@GetMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request, Model model) {
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "demob服务接口返回数据";
	}
}
