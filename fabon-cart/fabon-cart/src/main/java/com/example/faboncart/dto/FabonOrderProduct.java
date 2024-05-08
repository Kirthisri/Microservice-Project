package com.example.faboncart.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FabonOrderProduct  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	private boolean orderPlaced;
	private String productId;
	private int quantity;
	private FabonProduct productDetail;
	private FallbackAttribute fallbackAttribute;
	
	public FallbackAttribute getFallbackAttribute() {
		return fallbackAttribute;
	}
	public void setFallbackAttribute(FallbackAttribute fallbackAttribute) {
		this.fallbackAttribute = fallbackAttribute;
	}
	public FabonProduct getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(FabonProduct productDetail) {
		this.productDetail = productDetail;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	// Builder class definition
    public static class Builder {
        private String orderId;
        private boolean orderPlaced;
        private String productId;
        private int quantity;
        private FabonProduct productDetail;
        private FallbackAttribute fallbackAttribute;

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderPlaced(boolean orderPlaced) {
            this.orderPlaced = orderPlaced;
            return this;
        }

        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder productDetail(FabonProduct productDetail) {
            this.productDetail = productDetail;
            return this;
        }

        public Builder fallbackAttribute(FallbackAttribute fallbackAttribute) {
            this.fallbackAttribute = fallbackAttribute;
            return this;
        }

        public FabonOrderProduct build() {
            FabonOrderProduct orderProduct = new FabonOrderProduct();
            orderProduct.orderId = this.orderId;
            orderProduct.orderPlaced = this.orderPlaced;
            orderProduct.productId = this.productId;
            orderProduct.quantity = this.quantity;
            orderProduct.productDetail = this.productDetail;
            orderProduct.fallbackAttribute = this.fallbackAttribute;
            return orderProduct;
        }
    }
	
}
