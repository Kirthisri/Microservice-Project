package com.example.fabonproducts.dto;

import org.springframework.stereotype.Component;

@Component
public class FabonProductReaderObject {
	private String productId;
	private String productName;
	private String productPrice;
	private String productCost;
	private String productSalePrice;
	private String productDiscount;
	private String productUnits;
	private String productCategoryId;
	private String productCategoryName;
	private String productDivisionId;
	private String productDivisionName;
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
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public String getProductDivisionId() {
		return productDivisionId;
	}
	public void setProductDivisionId(String productDivisionId) {
		this.productDivisionId = productDivisionId;
	}
	public String getProductDivisionName() {
		return productDivisionName;
	}
	public void setProductDivisionName(String productDivisionName) {
		this.productDivisionName = productDivisionName;
	}
	
	
}
