package com.ultrapower.demo.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ultrapower.msa.sdk.jwt.JWTHelper;
import com.ultrapower.msa.sdk.util.Base64;
import com.ultrapower.msa.sdk.util.MsaUtil;

import io.jsonwebtoken.Claims;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Value("${zuul.adress}")
	private String zuulAdress; 
	
	private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		// String uri = request.get
//		String jwtheader = request.getHeader("Authorization");
//		if (jwtheader != null && !"".equals(jwtheader)) {
//			String PAYLOAD = jwtheader.split("\\.")[1];
//			String base64Decode = Base64.decodebase(PAYLOAD);
//			JSONObject json = JSON.parseObject(base64Decode);
//			String iss = (String) json.get("iss");
////			log.info("AK:"+iss+"  SK:"+MsaUtil.getSk(iss));
//			Claims s = JWTHelper.parserTokens(jwtheader, MsaUtil.getSk(iss));
//			if (s != null) {
////				log.info("s:"+s.toString());
//				return true;
//			}
//			log.info("-----jwt过期-------");
//		}
//		response.sendRedirect(zuulAdress+"/tsso/login");
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

}
