package com.example.faboncart.redis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.faboncart.dto.FabonUserCart;

@Repository
public class UserCartRedDao {
	
	public static final String HASH_KEY = "FabonCart";

	@Autowired
	RedisTemplate redisTemplate;
	
	public FabonUserCart saveCart(FabonUserCart cart) {
		System.out.println("UserProductRedDao - Data saved in redis ");
		redisTemplate.opsForHash().putIfAbsent(HASH_KEY, cart.getCartId(), cart);
		return cart;
	}
	
	public List<FabonUserCart> getAllCartDetailFromRedis() {
		System.out.println("UserProductRedDao - Get cart detail from redis ");
		return redisTemplate.opsForHash().values(HASH_KEY);
	}
	
	public FabonUserCart getCartDetailByCartId(String cartId) {
		System.out.println("UserProductRedDao - Get product detail by cartId from redis ");
		return (FabonUserCart) redisTemplate.opsForHash().get(HASH_KEY, cartId);
	}
}
