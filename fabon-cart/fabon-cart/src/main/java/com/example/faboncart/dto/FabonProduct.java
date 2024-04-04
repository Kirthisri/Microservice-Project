package com.example.faboncart.dto;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@RedisHash("Product")
public class FabonProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("productId")
	private String productId;
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("productPrice")
	private String productPrice;
	
	@JsonProperty("productCost")
	private String productCost;
	
	@JsonProperty("productSalePrice")
	private String productSalePrice;
	
	@JsonProperty("productDiscount")
	private String productDiscount;
	
	@JsonProperty("productUnits")
	private String productUnits;
	
	@JsonProperty("categoryId")
	private String categoryId;
	
	@JsonProperty("divisionId")
	private String divisionId;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCost() {
		return productCost;
	}
	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}
	public String getProductSalePrice() {
		return productSalePrice;
	}
	public void setProductSalePrice(String productSalePrice) {
		this.productSalePrice = productSalePrice;
	}
	public String getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}
	public String getProductUnits() {
		return productUnits;
	}
	public void setProductUnits(String productUnits) {
		this.productUnits = productUnits;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	@Override
	public String toString() {
		return "FabonProduct [productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + ", productCost=" + productCost + ", productSalePrice=" + productSalePrice
				+ ", productDiscount=" + productDiscount + ", productUnits=" + productUnits + ", categoryId="
				+ categoryId + ", divisionId=" + divisionId + "]";
	}
	
}
