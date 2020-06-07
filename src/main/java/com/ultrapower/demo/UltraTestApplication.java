package com.ultrapower.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.ultrapower.demo.config.EhcacheUtil;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@ComponentScan({ "com.ultrapower.demo" })
//开启fegin
@EnableFeignClients
//支持缓存
//@EnableCaching
public class UltraTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UltraTestApplication.class, args);
	}
//	@Bean
//	public EhcacheUtil aaa(){
//		return new EhcacheUtil();
//	}
}
