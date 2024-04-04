package com.example.fabonproducts;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBatchProcessing
@EnableFeignClients
@ComponentScan(basePackages = "com.example.fabonproducts")
public class FabonProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabonProductsApplication.class, args);
	}

}
