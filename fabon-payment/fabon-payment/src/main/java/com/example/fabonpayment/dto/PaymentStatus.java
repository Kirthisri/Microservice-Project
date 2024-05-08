package com.example.fabonpayment.dto;

public enum PaymentStatus {
    PAYMENT_INPROGRESS("PAYMENT_INPROGRESS"),
    PAYMENT_NOT_DONE("PAYMENT_NOT_DONE"),
    PAYMENT_COMPLETED("PAYMENT_COMPLETED"),
    PAYMENT_FAILED("PAYMENT_FAILED");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
