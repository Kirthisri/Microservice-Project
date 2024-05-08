package com.example.faboncart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.faboncart.dto.FabonOrderProduct;
import com.example.faboncart.dto.FabonUserCart;
import com.example.faboncart.service.FabonCartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/userCart")
public class UserCartController {

	public static final Logger log = LoggerFactory.getLogger(UserCartController.class);
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemp;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	FabonCartService fabonCartService;
	
	
	@PostMapping(value = "/addToCart")
	public FabonUserCart addUserCartProducts(@RequestBody FabonUserCart userCartDetail) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userCartDetail);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /addToCart  Error: " + e);
		}
		

		kafkaTemp.send("USER-CART-DETAIL", "addToCart", jsonUserProds);
		
		//add data to database
		fabonCartService.saveUserProductInCart(userCartDetail);	
		
		
		return userCartDetail;
	}
	
	@CacheEvict(key = "#cartId + '-' + #cartProductId", value = "FabonUserCart")
	@DeleteMapping(value = "/deleteCartProducts")
	public FabonUserCart updateCartProducts(@RequestBody FabonUserCart userCartDetail) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userCartDetail);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /updateCartProducts  Error: " + e);
		}
		kafkaTemp.send("USER-CART-DETAIL", "updateCart", jsonUserProds);
		
		//update data to database
		fabonCartService.deleteUserProductInCart(userCartDetail);
		
		return userCartDetail;
	}
	
	
	@CachePut(key = "#cartId + '-' + #cartProductId", value = "FabonUserCart")
	@PutMapping(value = "/placeOrder")
	public List<FabonUserCart> placeOrder(@RequestParam String cartId) {
			
		//update order place status
		List<FabonUserCart> cartList=fabonCartService.updateCartData(cartId);
		
		cartList.forEach(data -> {
			String jsonUserProds = "";
			try {
				jsonUserProds = mapper.writeValueAsString(data);
			} catch (JsonProcessingException e) {
				// Handle serialization error
				log.error("UserProductController: API - /placeOrder  Error: " + e);
			}
			kafkaTemp.send("USER-CART-DETAIL", "orderPlace", jsonUserProds);
		});
		
		return cartList;
	}
	
	@GetMapping(value="/getCartOfUser")
	@Cacheable(key = "#cartId + '-' + #cartProductId", value = "FabonUserCart")
	//@Retry(name = "productServiceCB")
	public List<FabonUserCart> getCartData(@RequestParam("cartId") String cartId) {
		
		List<FabonUserCart> cartDetails = fabonCartService.getCartDetailsByCartId(cartId);
		//System.out.println("getProductDetails() - # in cartDetails: " + cartDetails.size());
		
		return cartDetails;
	}
	
	@GetMapping(value="/getProdDetailsOfUser")
	@CircuitBreaker(name = "productServiceCB", fallbackMethod = "fallbackForPrd")	
	@Cacheable(key = "#cartId", value = "FabonOrderProduct")
	//@Retry(name = "productServiceCB")
	public List<FabonOrderProduct> getProductDetails(@RequestParam("cartId") String cartId) {
		
		List<FabonUserCart> cartDetails = fabonCartService.getCartDetailsByCartId(cartId);
		System.out.println("getProductDetails() - # in cartDetails: " + cartDetails.size());
		List<FabonOrderProduct> orderData = fabonCartService.getOrderDetailsOfCart(cartDetails);
		System.out.println("getProductDetails() - # in orderData: " + orderData.size());
		return orderData;
	}
	
	//@CacheEvict(key = "#productId", value = "FabonProduct")
	public List<FabonOrderProduct> fallbackForPrd(String cartId, Throwable ex) {
		
		List<FabonUserCart> cartDetails = fabonCartService.getCartDetailsByCartId(cartId);
		System.out.println("fallbackForPrd() - # in cartDetails: " + cartDetails.size());
		List<FabonOrderProduct> orderData = fabonCartService.getOrderDetailsOfCartFromCache(cartDetails);
		System.out.println("fallbackForPrd() - # in orderData: " + orderData.size());
		return orderData;
		
		//List<FabonOrderProduct> fallbackForPrdata = Stream.of(returnData).collect(Collectors.toList());
	}
		
}
