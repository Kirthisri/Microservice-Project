package com.example.fabonreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableSwagger
public class FabonReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabonReaderApplication.class, args);
	}

}
