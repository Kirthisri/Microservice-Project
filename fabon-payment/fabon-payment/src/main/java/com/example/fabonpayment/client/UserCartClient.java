package com.example.fabonpayment.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fabonpayment.dto.FabonUserCart;

@FeignClient(name="cart-service")
public interface UserCartClient {
	// it is a declarative interface, need to declare the methods going to be called
	// from other client service

	@GetMapping(value="/getCartOfUser")
	public List<FabonUserCart> getCartData(@RequestParam("cartId") String cartId);

}
