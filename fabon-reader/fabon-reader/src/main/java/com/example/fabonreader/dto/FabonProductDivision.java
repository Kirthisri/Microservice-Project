package com.example.fabonreader.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
//@Entity
//@Table(name="ProductDivision")
@Document(collection="FabonProductDivision")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabonProductDivision {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//private String id;
	private String divisionId;
	private String divisionName;
	private String categoryId;
	
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
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
}
