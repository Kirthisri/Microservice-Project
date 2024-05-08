package com.example.fabonpayment.dto;

import jakarta.persistence.Embeddable;

@Embeddable
public class UPIPayment {
	
	private String upiId;
	

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	
}
