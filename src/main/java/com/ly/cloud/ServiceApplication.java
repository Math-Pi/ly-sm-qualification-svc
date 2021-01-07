package com.ly.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** 
 * Class Name: DemoServiceApplication  
 * Description: 
 * 			系统管理项目服务
 * @author: yizhiqiang
 * @mail: yizhiqiang@ly-sky.com 
 * @date: 2018年1月17日
 * @version: 1.0
 */  
@SpringBootApplication(scanBasePackages={"com.ly.cloud"})
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker 
@ServletComponentScan
public class ServiceApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
	
}



	