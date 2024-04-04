package com.example.fabonuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.SpringVersion;


@SpringBootApplication
@EnableDiscoveryClient
public class FabonUserApplication {
	
	public static final Logger log = LoggerFactory.getLogger(FabonUserApplication.class);

	public static void main(String[] args) {
		log.info("********************** FabonUserApplication started ********************************");
		SpringApplication.run(FabonUserApplication.class, args);
		System.out.println("version: " + SpringVersion.getVersion());
		log.info("********************** FabonUserApplication ends ********************************");
	}

}
