package com.example.faboncart.clients;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.faboncart.dto.FabonProduct;

//commented since is used for reactive programming using web client
//@HttpExchange
@FeignClient(name="fabon-reader")

//@CircuitBreaker(name = "productServiceCB", fallbackMethod = "fallback")
public interface UserProductClient{
//it is a declarative interface, need to declare the methods going to be called
	//from other client service
	
	//commented since is used for reactive programming using web client
	//@GetExchange(value="/products/getProductDetails/{productId}")	
	@GetMapping(value="/fabonUser/productDetails/{productId}")
	public FabonProduct getProductDetailsById(@PathVariable("productId") String productId);		
}
