package com.ultrapower.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "v1/auth", description = "jwt票据操作接口", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value = "/v1")
public class MainController {


	@ApiOperation(value = "申请JWT", notes = "申请JWT", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	@ResponseBody
	@ApiResponses(@ApiResponse(code = 200, message = ""))
	public String go(HttpServletRequest request, Model model) {
		return "main";
	}
}
