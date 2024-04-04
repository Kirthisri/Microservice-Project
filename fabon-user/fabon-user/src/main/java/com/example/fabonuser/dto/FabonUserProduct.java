package com.example.fabonuser.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FabonUserProduct {
	private String emailId;
	private List<String> cartProductIds;
	private List<String> wishlistProductIds;
	private boolean orderPlaced;
	
	public boolean isOrderPlaced() {
		return orderPlaced;
	}
	public void setOrderPlaced(boolean orderPlaced) {
		this.orderPlaced = orderPlaced;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<String> getCartProductIds() {
		return cartProductIds;
	}
	public void setCartProductIds(List<String> cartProductIds) {
		this.cartProductIds = cartProductIds;
	}
	public List<String> getWishlistProductIds() {
		return wishlistProductIds;
	}
	public void setWishlistProductIds(List<String> wishlistProductIds) {
		this.wishlistProductIds = wishlistProductIds;
	}
	
}
