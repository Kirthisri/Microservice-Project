package com.example.fabonreader.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FabonUserOrderDetails {
	private String orderId;
	private List<FabonProducts> orderedProducts;
	private String totalAmountPayable;
	
	public String getTotalAmountPayable() {
		return totalAmountPayable;
	}
	public void setTotalAmountPayable(String totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<FabonProducts> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<FabonProducts> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

}
