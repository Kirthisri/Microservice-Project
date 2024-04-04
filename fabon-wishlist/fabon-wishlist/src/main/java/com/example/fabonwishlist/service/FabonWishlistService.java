package com.example.fabonwishlist.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.fabonwishlist.dto.FabonUserWishlist;
import com.example.fabonwishlist.redisdao.WishlistRedisDao;

@Service
public class FabonWishlistService {
	
	public static final Logger log = LoggerFactory.getLogger(FabonWishlistService.class);
	
	@Autowired
	WishlistRedisDao wishlistRedisDao;

	public void saveUserProductInWishlist(FabonUserWishlist userWLDetail) {
		wishlistRedisDao.save(userWLDetail);
	}
	public void deleteUserProductInWishlist(String wlprdId) {
		wishlistRedisDao.deleteProduct(wlprdId);
	}
	public void updateInStockStatusTrue(FabonUserWishlist userWLDetail) {
		wishlistRedisDao.updateProduct(userWLDetail);
	}
	public FabonUserWishlist getUserProductInWishlist(String wlprdId) {
		return wishlistRedisDao.findProductById(wlprdId);
	}

//	public void deleteUserProductInWishlist(FabonUserWishlist userWLDetail) {
//		fabonCartDao.deleteByEmailAndProductId(userWLDetail.getEmailId(), userWLDetail.getWishlistProductId());
//	}
	

	/*
	 * public void updateInStockStatus(FabonUserWishlist userWLDetail) {
	 * fabonCartDao.updateOrderPlacedByEmailId(userWLDetail.getEmailId(),
	 * userWLDetail.isInStock()); }
	 */

//	public List<FabonUserWishlist> getUserProductInWishlist(String wishlistProductId) {
//		return fabonCartDao.findByWishlistProductId(wishlistProductId);
//	}
//
//	public void deleteProductByPrdId(String wishlistProductId) {
//		fabonCartDao.deleteByWishlistProductId(wishlistProductId);
//	}
//	
//	public void updateInStockStatusTrue(String productId) {
//		fabonCartDao.updateInStockByWishlistProductId(productId);
//	}
}
