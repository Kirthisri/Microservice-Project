package com.example.fabonreader.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
//@Entity
//@Table(name="UserCartDetails")
@Document(collection="FabonUserCartDetails")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabonUserCartDetails {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//private Long id;
	//@Indexed(unique = true)
	private String emailId;
	private List<String> cartProductIds;
	private List<String> wishlistProductIds;
	private String orderId;
	private boolean orderPlaced;
	private boolean proceedPayment;
	private double totalAmountPayable;
	
	public double getTotalAmountPayable() {
		return totalAmountPayable;
	}
	public void setTotalAmountPayable(double totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
	}
	public boolean isProceedPayment() {
		return proceedPayment;
	}
	public void setProceedPayment(boolean proceedPayment) {
		this.proceedPayment = proceedPayment;
	}
	public boolean isOrderPlaced() {
		return orderPlaced;
	}
	public void setOrderPlaced(boolean orderPlaced) {
		this.orderPlaced = orderPlaced;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<String> getCartProductIds() {
		return cartProductIds;
	}
	public void setCartProductIds(List<String> cartProductIds) {
		this.cartProductIds = cartProductIds;
	}
	public List<String> getWishlistProductIds() {
		return wishlistProductIds;
	}
	public void setWishlistProductIds(List<String> wishlistProductIds) {
		this.wishlistProductIds = wishlistProductIds;
	}
	@Override
	public String toString() {
		return "FabonUserCartDetails [emailId=" + emailId + ", cartProductIds=" + cartProductIds
				+ ", wishlistProductIds=" + wishlistProductIds + ", orderId=" + orderId + ", orderPlaced=" + orderPlaced
				+ ", proceedPayment=" + proceedPayment + ", totalAmountPayable=" + totalAmountPayable + "]";
	}
	
	
}
