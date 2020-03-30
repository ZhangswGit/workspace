package com.ultrapower.demo.config;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ultrapower.msa.sdk.jwt.JWTHelper;
import com.ultrapower.msa.sdk.util.AESEncode_Decode_Util;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignInterceptor implements RequestInterceptor {

	String AK = "TSSO";
	String SK = "6abe9092-8f33-4688-b343-476d2f6da9ae";
	long expired_in = 10000;
	String type = "app";
	String consumer = "";
	String applicationId = "TSSO";
	String applicationName = "TSSO";

	/**
	 * Feign调用的时候添加请求头
	 */
	@Override
	public void apply(RequestTemplate requestTemplate) {
		// 针对整个请求链中的request
		HttpServletRequest attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String msaRequestId = attributes.getHeader("X-Request-Id");
		if (StringUtils.isBlank(msaRequestId)) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			requestTemplate.header("X-Request-Id", uuid);
		} else {
			requestTemplate.header("X-Request-Id", msaRequestId);
		}
		// 针对服务端发起的请求
		Request request = requestTemplate.request();
		Map<String, Collection<String>> headers = request.headers();
		if (!headers.containsKey("Authorization")) {
			// 通过网关访问 生成全局JWT
			String createJWT = "";
			try {
				createJWT = JWTHelper.createJWT(AK, expired_in, consumer, type, applicationId, applicationName, AESEncode_Decode_Util.aesDecryptDefault(SK));
			} catch (Exception e) {
				createJWT = JWTHelper.createJWT(AK, expired_in, consumer, type, applicationId, applicationName, SK);
			}
			requestTemplate.header("Authorization", createJWT);
		}
	}
}
