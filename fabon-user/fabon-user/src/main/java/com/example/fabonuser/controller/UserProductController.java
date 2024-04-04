package com.example.fabonuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.fabonuser.clients.UserProductClient;
import com.example.fabonuser.dto.FabonProducts;
import com.example.fabonuser.dto.FabonUserProduct;
import com.example.fabonuser.service.FabonUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/userProducts")
public class UserProductController {

	public static final Logger log = LoggerFactory.getLogger(UserProductController.class);
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemp;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	FabonUserService fabonUserService;
	
	@PostMapping(value = "/addToCart")
	public FabonUserProduct addUserCartProducts(@RequestBody FabonUserProduct userProducts) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userProducts);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /addToCart  Error: " + e);
		}
		

		kafkaTemp.send("USER-CART-DETAIL", "addToCart", jsonUserProds);
		
		return userProducts;
	}
	
	@PostMapping(value = "/updateCartProducts")
	public FabonUserProduct updateCartProducts(@RequestBody FabonUserProduct userProducts) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userProducts);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /updateCartProducts  Error: " + e);
		}
		kafkaTemp.send("USER-CART-DETAIL", "updateCart", jsonUserProds);
		return userProducts;
	}
	
	@PostMapping(value = "/placeOrder")
	public FabonUserProduct placeOrder(@RequestBody FabonUserProduct userProducts) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userProducts);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /placeOrder  Error: " + e);
		}
		kafkaTemp.send("USER-CART-DETAIL", "orderPlace", jsonUserProds);
		return userProducts;
	}
	
	@PostMapping(value = "/addToWishList")
	public FabonUserProduct addUserWishlistProducts(@RequestBody FabonUserProduct userProducts) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userProducts);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /addToWishList  Error: " + e);
		}
		kafkaTemp.send("USER-WISHLIST-DETAIL", "addToWishList", jsonUserProds);
		return userProducts;
	}
	
	@PostMapping(value = "/deleteFromWishList")
	public FabonUserProduct deleteUserWishlistProducts(@RequestBody FabonUserProduct userProducts) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userProducts);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /deleteFromWishList  Error: " + e);
		}
		kafkaTemp.send("USER-WISHLIST-DETAIL", "deleteFromWishList", jsonUserProds);
		return userProducts;
	}
	

}
