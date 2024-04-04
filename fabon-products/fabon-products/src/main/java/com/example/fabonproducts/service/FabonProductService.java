package com.example.fabonproducts.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.fabonproducts.dao.FabonCategoryDao;
import com.example.fabonproducts.dao.FabonDivisionDao;
import com.example.fabonproducts.dao.FabonProductDao;
import com.example.fabonproducts.dto.FabonProductCategory;
import com.example.fabonproducts.dto.FabonProductDivision;
import com.example.fabonproducts.dto.FabonProducts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FabonProductService {

	private static final Logger log = LoggerFactory.getLogger(FabonProductService.class);
	
	@Autowired
	FabonProductDao fabonProductDao;
	
	@Autowired
	FabonCategoryDao categoryDao;
	
	@Autowired
	FabonDivisionDao divisionDao;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public void addProductToDB(List<FabonProducts> fabonProducts) {
		
		fabonProductDao.saveAll(fabonProducts);		
	}

	public List<FabonProducts> getAllProductsInDB() {
		return fabonProductDao.findAll();
	}

	public FabonProducts getProductDetailsById(String productId) {
		return fabonProductDao.findByProductId(productId);
	}

	public void sendProductDetailsToKafka() {
		List<FabonProducts> products = fabonProductDao.findAll();
		if(null != products) {
			String productString;
			try {
				productString = mapper.writeValueAsString(products);			
				kafkaTemplate.send("PRODUCT-DETAIL", productString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendProductCategoryDetailsToKafka() {
		List<FabonProductCategory> categories = categoryDao.findAll();
		if(null != categories) {
			String catgString;
			try {
				catgString = mapper.writeValueAsString(categories);			
				kafkaTemplate.send("CATEGORY-DETAIL", catgString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendProductDivisionDetailsToKafka() {
		List<FabonProductDivision> divisions = divisionDao.findAll();
		if(null != divisions) {
			String divisionString;
			try {
				divisionString = mapper.writeValueAsString(divisions);			
				kafkaTemplate.send("DIVISION-DETAIL", divisionString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveAllProducts(List<FabonProducts> productList) {
		productList.forEach(product -> {
			FabonProducts existingProduct = fabonProductDao.findByProductId(product.getProductId());
			if(null != existingProduct) {
				System.out.println("product " + product.getProductId() + " already exist");
				product.setId(existingProduct.getId());
				fabonProductDao.deleteByProductId(product.getProductId());
				fabonProductDao.save(product);
			}
			else {
				System.out.println("product " + product.getProductId() + " saved");
				fabonProductDao.save(product);
			}
		});
	}

	public void saveAllProductDivisions(List<FabonProductDivision> divisionList) {
		divisionList.forEach(division -> {
			FabonProductDivision existingDivision = divisionDao.findByDivisionId(division.getDivisionId());
			if(null != existingDivision) {
				System.out.println("division " + division.getDivisionId() + " already exist");
				division.setId(existingDivision.getId());
				divisionDao.deleteByDivisionId(division.getDivisionId());
				divisionDao.save(division);
			}
			else {
				System.out.println("division " + division.getDivisionId() + " saved");
				divisionDao.save(division);
			}
		});
	}

	public void saveAllProductCategories(List<FabonProductCategory> categoryList) {
		categoryList.forEach(category -> {
			FabonProductCategory existingCategory = categoryDao.findByCategoryId(category.getCategoryId());
			if(null != existingCategory) {
				System.out.println("category " + category.getCategoryId() + " already exist");
				category.setId(existingCategory.getId());
				categoryDao.deleteByCategoryId(category.getCategoryId());
				categoryDao.save(category);
			}
			else {
				System.out.println("category " + category.getCategoryId() + " saved");
				categoryDao.save(category);
			}
		});
	}
	
}
