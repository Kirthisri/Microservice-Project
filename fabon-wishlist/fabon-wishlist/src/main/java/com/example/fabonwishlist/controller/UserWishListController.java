package com.example.fabonwishlist.controller;

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

import com.example.fabonwishlist.dto.FabonUserWishlist;
import com.example.fabonwishlist.service.FabonWishlistService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/userWishList")
public class UserWishListController {

	public static final Logger log = LoggerFactory.getLogger(UserWishListController.class);

	@Autowired
	KafkaTemplate<String, String> kafkaTemp;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	FabonWishlistService fabonWishlistService;
	
	@PostMapping(value = "/addToWishList")
	public FabonUserWishlist addUserWishlistProducts(@RequestBody FabonUserWishlist userProducts) {
		// converting the request object to string
		String jsonUserProds = "";
		try {
			jsonUserProds = mapper.writeValueAsString(userProducts);
		} catch (JsonProcessingException e) {
			// Handle serialization error
			log.error("UserProductController: API - /addToWishList  Error: " + e);
		}
		//kafkaTemp.send("USER-WISHLIST-DETAIL", "addToWishList", jsonUserProds);
		
		fabonWishlistService.saveUserProductInWishlist(userProducts);
		
		
		return userProducts;
	}

	@Cacheable(key="#wishlistProductId", value="Wishlist")
	@GetMapping(value="/getWishlist")
	public FabonUserWishlist getUserWishlistProducts(@RequestParam String wishlistProductId) {
		return fabonWishlistService.getUserProductInWishlist(wishlistProductId);		
	}
	
	
	@CacheEvict(key="#wishlistProductId", value="Wishlist")
	@DeleteMapping(value = "/deleteFromWishList")
	public String deleteUserWishlistByProductId(@RequestParam String wishlistProductId) {		
		
		fabonWishlistService.deleteUserProductInWishlist(wishlistProductId);
		
		return "Product removed from wishlist";
	}
	
	@CachePut(key="#wishlistProductId", value="Wishlist")
	@PutMapping(value = "/updateStockDetail")
	public String updateStockDataInWishlist(@RequestParam String wishlistProductId) {		
		FabonUserWishlist userProducts = fabonWishlistService.getUserProductInWishlist(wishlistProductId);
		userProducts.setInStock(false);
		fabonWishlistService.updateInStockStatusTrue(userProducts);
		
		return "Product removed from wishlist";
	}
	
//	@DeleteMapping(value = "/deleteFromWishList")
//	public FabonUserWishlist deleteUserWishlistProducts(@RequestBody FabonUserWishlist userProducts) {
//		// converting the request object to string
//		String jsonUserProds = "";
//		try {
//			jsonUserProds = mapper.writeValueAsString(userProducts);
//		} catch (JsonProcessingException e) {
//			// Handle serialization error
//			log.error("UserProductController: API - /deleteFromWishList  Error: " + e);
//		}
//		//kafkaTemp.send("USER-WISHLIST-DETAIL", "deleteFromWishList", jsonUserProds);
//		
//		fabonWishlistService.deleteUserProductInWishlist(userProducts);
//		
//		return userProducts;
//	}

	
}
