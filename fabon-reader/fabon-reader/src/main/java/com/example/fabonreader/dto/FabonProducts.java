package com.example.fabonreader.dto;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Component
//@Entity
//@Table(name="Products")
@Document(collection="Products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabonProducts implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//private Long id;
	@JsonProperty("productId")
	private String productId;
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("productPrice")
	private double productPrice;
	
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
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
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
}
