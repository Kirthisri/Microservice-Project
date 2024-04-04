package com.example.fabonwishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FabonWishListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabonWishListServiceApplication.class, args);
	}

}
