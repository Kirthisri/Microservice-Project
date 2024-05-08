package com.example.faboncart.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.faboncart.dto.FabonProduct;
import com.example.faboncart.redis.dao.UserProductRedDao;

@Component
public class UserProductClientCache {
	
	private UserProductRedDao userProductRedDao;

	@Autowired
	public UserProductClientCache(UserProductRedDao userProductRDao) {
		userProductRedDao = userProductRDao;
	}	

	@Cacheable(key = "#productId", value = "FabonProduct")
    public FabonProduct callCachedProductDetailsById(String productId) {		
		FabonProduct prdDetailFromRedisCache = userProductRedDao.getProductDetailByPrdId(productId);
		System.out.println("Log is in cache - callCachedProductDetailsById");
        return prdDetailFromRedisCache;
    }
}
