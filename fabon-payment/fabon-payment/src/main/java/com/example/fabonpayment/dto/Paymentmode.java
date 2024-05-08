package com.example.fabonpayment.dto;

public enum Paymentmode {
		CASH("CASH"),
		UPI("UPI"),
		DEBIT_CARD("DEBIT_CARD"),
		CREDIT_CARD("CREDIT_CARD"),
		GPAY("GPAY");
		
	    private final String value;

	    Paymentmode(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
}
