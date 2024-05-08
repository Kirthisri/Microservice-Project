package com.example.faboncart.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name="CartDetail")
public class FabonUserCart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
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
