package com.example.fabonreader.kafka.listener;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.fabonreader.dao.FabonProductCategoryDao;
import com.example.fabonreader.dao.FabonProductDivisionDao;
import com.example.fabonreader.dao.FabonUserCartDetailDao;
import com.example.fabonreader.dao.FabonUserProductDao;
import com.example.fabonreader.dao.FabonUsersDao;
import com.example.fabonreader.dto.FabonProductCategory;
import com.example.fabonreader.dto.FabonProductDivision;
import com.example.fabonreader.dto.FabonProducts;
import com.example.fabonreader.dto.FabonUserCartDetails;
import com.example.fabonreader.dto.FabonUsers;
import com.example.fabonreader.service.FabonProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FabonUserListener {
	
	//public static final Logger log = LoggerFactory.getLogger(FabonUserListener.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	FabonUsersDao userDao;
	
	@Autowired
	FabonUserCartDetailDao cartDetailDao;
	
	@Autowired
	FabonUserProductDao productDao;
	
	@Autowired
	FabonProductCategoryDao categoryDao;
	
	@Autowired
	FabonProductDivisionDao divisionDao;
	
	@Autowired
	FabonProductService productService;
	
	@KafkaListener(topics="USER-TOPIC", groupId="group_id")
	public void userCartDetailMessage(@Payload String event, @Payload String userProducts){
		System.out.println("reading kafka message of USER-TOPIC - message: " + userProducts);
		//FabonUsers userDetails = new Gson().fromJson(userProducts, FabonUsers.class);
		//FabonUsers userDetails = mapper.readValue(userProducts, new com.fasterxml.jackson.core.type.TypeReference<FabonUsers>() {});
		
		  JSONObject jsonObject = new JSONObject(userProducts);
		  
		  FabonUsers userDetails = new FabonUsers();
		  userDetails.setFullname(jsonObject.getString("fullname"));
		  userDetails.setEmailId(jsonObject.getString("emailId"));
		  userDetails.setPhnNum(jsonObject.getString("phnNum"));
		  userDetails.setGender(jsonObject.getString("gender"));
		  userDetails.setPassword(jsonObject.getString("password"));
		  userDetails.setPlace(jsonObject.getString("place"));

		try {
			userDao.save(userDetails);
		}
		catch(Exception e) {
			System.out.println(userProducts);
		}
	}
	
	@KafkaListener(topics="PRODUCT-DETAIL", groupId="group_id")
	public void userProductDetailMessage(@Payload String message) throws JsonMappingException, JsonProcessingException{
		System.out.println("reading kafka message of PRODUCT-DETAIL - message: " + message);
		
		List<FabonProducts> products = mapper.readValue(message, 
				new com.fasterxml.jackson.core.type.TypeReference<List<FabonProducts>>() {});
				
		/*
		 * JSONObject jsonObject = new JSONObject(message);
		 * 
		 * FabonProducts products = new FabonProducts();
		 * products.setProductId(jsonObject.getString("productId"));
		 * products.setProductName(jsonObject.getString("productName"));
		 * products.setProductPrice(jsonObject.getString("productPrice"));
		 * products.setProductCost(jsonObject.getString("productCost"));
		 * products.setProductDiscount(jsonObject.getString("productDiscount"));
		 * products.setProductUnits(jsonObject.getString("productUnits"));
		 * products.setProductSalePrice(jsonObject.getString("productSalePrice"));
		 * products.setProductCategoryId(jsonObject.getString("productCategoryId"));
		 * products.setProductDivisionId(jsonObject.getString("productDivisionId"));
		 */
		
		try {
			productDao.saveAll(products);
		}catch(Exception e) {
			System.out.println(message);
		}
		
		
	}
	
	@KafkaListener(topics="CATEGORY-DETAIL", groupId="group_id")
	public void userProductCategoryDetailMessage(@Payload String message) throws JsonMappingException, JsonProcessingException {	
		System.out.println("reading kafka message of CATEGORY-DETAIL - message: " + message);
		
		List<FabonProductCategory> catgs = mapper.readValue(message, 
				new com.fasterxml.jackson.core.type.TypeReference<List<FabonProductCategory>>() {});
		
		/*
		 * JSONObject jsonObject = new JSONObject(message);
		 * 
		 * FabonProductCategory catgs = new FabonProductCategory();
		 * catgs.setCategoryId(jsonObject.getString("categoryId"));
		 * catgs.setCategoryName(jsonObject.getString("categoryName"));
		 */
		
		try {
			categoryDao.saveAll(catgs);
		}
		catch(Exception e) {
			System.out.println(message);
		}
		
	}
	
	@KafkaListener(topics="DIVISION-DETAIL", groupId="group_id")
	public void userProductDivisionDetailMessage(@Payload String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("reading kafka message of DIVISION-DETAIL - message: " + message);
		
		List<FabonProductDivision> divs = mapper.readValue(message, 
				new com.fasterxml.jackson.core.type.TypeReference<List<FabonProductDivision>>() {});
		
		//JSONObject jsonObject = new JSONObject(message);
		
		/*
		 * FabonProductDivision divs = new FabonProductDivision();
		 * divs.setDivisionId(jsonObject.getString("divisionId"));
		 * divs.setDivisionName(jsonObject.getString("divisionName"));
		 */
		
		try {
			divisionDao.saveAll(divs);
		}
		catch(Exception e) {
			System.out.println(message);
		}
		
	}
	
	@KafkaListener(topics = "USER-CART-DETAIL", groupId = "group_id")
	public void userCartProductDetails(ConsumerRecord<String, String> message)
			throws JsonMappingException, JsonProcessingException {
		System.out.println("reading kafka message of USER-CART-DETAIL - message: " + message);

		FabonUserCartDetails cartDetails = mapper.readValue(message.value(),
				new com.fasterxml.jackson.core.type.TypeReference<FabonUserCartDetails>() {
				});
		
		try {
			if(null != message.key() && message.key().equals("addToCart")) {
				cartDetailDao.save(cartDetails);
			}
			else if(null != message.key() && message.key().equals("updateCart")) {
				productService.updateCartProductIdsByEmailId(cartDetails.getEmailId(), cartDetails.getCartProductIds());
				//cartDetailDao.updateCartProductIdsByEmailId(cartDetails.getEmailId(), cartDetails.getCartProductIds());
			}	
			else if (null != message.key() && message.key().equals("orderPlace")) {
				productService.updateOrderPlacedByEmailId(cartDetails.getEmailId(), cartDetails.isOrderPlaced());
			}
		}
		catch(Exception e) {
			System.out.println(message);
		}
		

	}
}
