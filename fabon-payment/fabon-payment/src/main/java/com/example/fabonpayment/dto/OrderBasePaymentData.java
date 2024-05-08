package com.example.fabonpayment.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Payment")
@Component
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
public class OrderBasePaymentData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String orderId;
	private String paymentMode;
	private String paymentStatus;
	
	private UPIPayment upiData;
	
	private CardPayment cartData;
    
	private Long transactionId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public UPIPayment getUpiData() {
		return upiData;
	}
	public void setUpiData(UPIPayment upiData) {
		this.upiData = upiData;
	}
	public CardPayment getCartData() {
		return cartData;
	}
	public void setCartData(CardPayment cartData) {
		this.cartData = cartData;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	public static class Builder {
		private Long id;
	    private String orderId;
	    private String paymentMode;
	    private String paymentStatus;
	    private UPIPayment upiData;
	    private CardPayment cartData;
	    private Long transactionId;
	    
	    public Builder id(Long id) {
	        this.id = id;
	        return this;
	    }

	    public Builder orderId(String orderId) {
	        this.orderId = orderId;
	        return this;
	    }


	    public Builder paymentMode(String paymentMode) {
	        this.paymentMode = paymentMode;
	        return this;
	    }

	    public Builder paymentStatus(String paymentStatus) {
	        this.paymentStatus = paymentStatus;
	        return this;
	    }

	    public Builder upiData(UPIPayment upiData) {
	        this.upiData = upiData;
	        return this;
	    }

	    public Builder cartData(CardPayment cartData) {
	        this.cartData = cartData;
	        return this;
	    }

	    public Builder transactionId(Long transactionId) {
	        this.transactionId = transactionId;
	        return this;
	    }

	    public OrderBasePaymentData build() {
	        OrderBasePaymentData orderBasePaymentData = new OrderBasePaymentData();
	        orderBasePaymentData.setId(id);
	        orderBasePaymentData.setOrderId(orderId);
	        orderBasePaymentData.setPaymentMode(paymentMode);
	        orderBasePaymentData.setPaymentStatus(paymentStatus);
	        orderBasePaymentData.setUpiData(upiData);
	        orderBasePaymentData.setCartData(cartData);
	        orderBasePaymentData.setTransactionId(transactionId);
	        return orderBasePaymentData;
	    }
	}

	
}
