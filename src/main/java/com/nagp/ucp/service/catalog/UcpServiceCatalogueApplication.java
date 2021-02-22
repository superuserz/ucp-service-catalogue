package com.nagp.ucp.service.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@ComponentScan({ "com.nagp.ucp" })
@EnableFeignClients
public class UcpServiceCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcpServiceCatalogueApplication.class, args);
	}

}
