package com.example.fabonpayment.dto;

public class FabonUserCart {
	private String cartId;
	private String cartProductId;
	private int quantity;
	private String orderId;
	private boolean orderPlaced;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getCartProductId() {
		return cartProductId;
	}
	public void setCartProductId(String cartProductId) {
		this.cartProductId = cartProductId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public boolean isOrderPlaced() {
		return orderPlaced;
	}
	public void setOrderPlaced(boolean orderPlaced) {
		this.orderPlaced = orderPlaced;
	}
	
	
}
