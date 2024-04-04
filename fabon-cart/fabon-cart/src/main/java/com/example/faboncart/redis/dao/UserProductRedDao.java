package com.example.faboncart.redis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.faboncart.dto.FabonProduct;

@Repository
public class UserProductRedDao{
	
	public static final String HASH_KEY = "FabonProduct";

	@Autowired
	RedisTemplate redisTemplate;
	
	public FabonProduct saveProduct(FabonProduct product) {
		System.out.println("UserProductRedDao - Data saved in redis ");
		redisTemplate.opsForHash().putIfAbsent(HASH_KEY, product.getProductId(), product);
		return product;
	}
	
	public List<FabonProduct> getAllProductDetailFromRedis() {
		System.out.println("UserProductRedDao - Get product detail from redis ");
		return redisTemplate.opsForHash().values(HASH_KEY);
	}
	
	public FabonProduct getProductDetailByPrdId(String productId) {
		System.out.println("UserProductRedDao - Get product detail by prdId from redis ");
		return (FabonProduct) redisTemplate.opsForHash().get(HASH_KEY, productId);
	}
}
