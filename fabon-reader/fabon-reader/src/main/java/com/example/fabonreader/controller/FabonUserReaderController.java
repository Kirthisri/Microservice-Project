package com.example.fabonreader.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabonreader.dto.FabonExceptionMessage;
import com.example.fabonreader.dto.FabonProductCategory;
import com.example.fabonreader.dto.FabonProductDivision;
import com.example.fabonreader.dto.FabonProducts;
import com.example.fabonreader.dto.FabonUserCartDetails;
import com.example.fabonreader.dto.FabonUserOrderDetails;
import com.example.fabonreader.dto.FabonUsers;
import com.example.fabonreader.dto.ResponseData;
import com.example.fabonreader.exception.FabonReaderExceptions;
import com.example.fabonreader.service.FabonProductService;
import com.example.fabonreader.service.FabonUserService;
import com.example.fabonuser.util.ExceptionConstants;


@RequestMapping("/fabonUser")
@RestController
public class FabonUserReaderController {

	@Autowired
	FabonUserService fabonUserService;
	
	@Autowired
	FabonProductService fabonProductService;
	
	/*
	 * @PostMapping(value="/signup") public FabonUsers saveUserData(@RequestBody
	 * FabonUsers users) {
	 * 
	 * fabonUsersDao.save(users);
	 * 
	 * return users; }
	 */
		
	@GetMapping(value = "/login")
	public ResponseData fetchUserData(@RequestBody FabonUsers users) throws FabonReaderExceptions {
		if (null != users.getEmailId() && null != users.getPassword()) {
			Optional<FabonUsers> userDetail = fabonUserService.getUserByEmailId(users.getEmailId());

			if (userDetail.isPresent() && users.getPassword().equals(userDetail.get().getPassword())) {
				return new ResponseData.Builder().data(userDetail.get()).responseCode("200")
						.responseMessage("login successful").build();

			} else {
				return new ResponseData.Builder().responseCode("801")
						.responseMessage("Incorrect User email id or Password").build();
			}
		} else {
			return new ResponseData.Builder().responseCode("802")
					.responseMessage("Enter registered email id and password to login").build();
		}
	}

	
	@GetMapping(value="/getUserDetail/{userId}")
	public FabonUsers fetchUserData(@PathVariable Long userId) throws FabonReaderExceptions {	
		Optional<FabonUsers> userDet = fabonUserService.getUserDetailsById(userId);
		
		if(userDet.isPresent()) {
			return userDet.get();
		}
		else {
			throw new FabonReaderExceptions(ExceptionConstants.INVALID_USER_ID, ExceptionConstants.INCORRECT_USER_ID);
		}
	}
	
	@GetMapping(value="/getAllProductCategory")
	public List<FabonProductCategory> getAllProducts() {
		List<FabonProductCategory> categories = new ArrayList<>();
	    try {
	    	categories = fabonProductService.getAllProductsCategories();
	    } catch (Exception e) {
	        return Collections.emptyList();  // Return an empty list or handle the error accordingly
	    }
		return categories;
	}
	
	@GetMapping(value="/divisionsOfCategory/{categoryId}")
	public List<FabonProductDivision> getAllDivisionsOfCategory(@PathVariable("categoryId") String categoryId) {
		List<FabonProductDivision> products = new ArrayList<>();
	    try {
	    	products = fabonProductService.getAllDivisionOfCategory(categoryId);
	    } catch (Exception e) {
	        return Collections.emptyList();  // Return an empty list or handle the error accordingly
	    }
		return products;
	}
	
	@GetMapping(value="/productsOfDivision/{divisionId}")
	public List<FabonProducts> getAllProductsOfDivision(@PathVariable("divisionId") String divisionId) {
		List<FabonProducts> products = new ArrayList<>();
	    try {
	    	products = fabonProductService.getAllProductsOfDivision(divisionId);
	    } catch (Exception e) {
	        return Collections.emptyList();  // Return an empty list or handle the error accordingly
	    }
		return products;
	}
	
	@GetMapping(value="/productDetails/{productId}")
	public FabonProducts getProductDetailsById(@PathVariable("productId") String productId) {
		return fabonProductService.getProductDetailsByProductId(productId);
	}
	
	@GetMapping(value="/showCartDetails")
	public List<FabonProducts> showUserCartProductDetail(@RequestBody FabonUserCartDetails cartDetail) {
		return fabonProductService.getCartProducts(cartDetail.getEmailId());
	}
	
	@GetMapping(value="/showOrderDetails")
	public FabonUserOrderDetails showUserOrderDetail(@RequestBody FabonUserCartDetails cartDetail) {
		return fabonProductService.getUserOrderedProducts(cartDetail.getEmailId());
	}
	
}
