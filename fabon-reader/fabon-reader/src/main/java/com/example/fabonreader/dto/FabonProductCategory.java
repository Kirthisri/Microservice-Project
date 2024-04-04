package com.example.fabonreader.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Id;

@Component
//@Entity
//@Table(name="ProductCategory")
@Document(collection="FabonProductCategory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabonProductCategory {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//private Long id;
	private String categoryId;
	private String categoryName;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
