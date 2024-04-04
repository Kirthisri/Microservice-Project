package com.example.fabonwishlist.dto;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

@Component
//@Entity
//@Table(name="WishlistDetail")
@RedisHash("Wishlist")
public class FabonUserWishlist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emailId;
	private String wishlistProductId;
	private boolean inStock;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getWishlistProductId() {
		return wishlistProductId;
	}
	public void setWishlistProductId(String wishlistProductId) {
		this.wishlistProductId = wishlistProductId;
	}
	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	
}
