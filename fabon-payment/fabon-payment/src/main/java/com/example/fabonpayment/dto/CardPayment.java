package com.example.fabonpayment.dto;

import jakarta.persistence.Embeddable;

@Embeddable
public class CardPayment {
	
	private String cardNumber;
	private String expiry;
	private String CVV;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	
	
}
