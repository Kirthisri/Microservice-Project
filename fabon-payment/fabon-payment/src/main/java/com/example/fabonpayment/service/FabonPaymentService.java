package com.example.fabonpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fabonpayment.dao.FabonPaymentDao;
import com.example.fabonpayment.dto.OrderBasePaymentData;
import com.example.fabonpayment.util.FabonPaymentUtil;


@Service
public class FabonPaymentService {
	
	@Autowired
	FabonPaymentDao paymentDao;

	public boolean validatePayment(OrderBasePaymentData paymentDetail) {
		if(null == paymentDetail.getOrderId()) {
			return false;
		}
		
		if(null == paymentDetail.getPaymentMode()) {
			return false;
		}
		
		if(0L == FabonPaymentUtil.generateRandomTransactionId()) {
			return false;
		}
		else {
			paymentDetail.setTransactionId(FabonPaymentUtil.generateRandomTransactionId());
		}

		return true;
	}

	@Transactional
	public void savePaymentDetails(OrderBasePaymentData paymentDetail) {
		paymentDao.save(paymentDetail);
	}

	@Transactional
	public void updatePaymentDetails(OrderBasePaymentData paymentDetail) {
	    OrderBasePaymentData existingPaymentDetail = paymentDao.findByOrderId(paymentDetail.getOrderId());
	    if (existingPaymentDetail != null) {
	        // Update fields from the provided paymentDetail to the existingPaymentDetail
	        existingPaymentDetail.setPaymentMode(paymentDetail.getPaymentMode());
	        existingPaymentDetail.setPaymentStatus(paymentDetail.getPaymentStatus());
	        existingPaymentDetail.setUpiData(paymentDetail.getUpiData());
	        //existingPaymentDetail.getUpiData().setId(existingPaymentDetail.getUpiData().getId());
	        //existingPaymentDetail.getUpiData().setUpiId(paymentDetail.getUpiData().getUpiId());
	        
	        existingPaymentDetail.setCartData(paymentDetail.getCartData());
	        //existingPaymentDetail.getCartData().setId(existingPaymentDetail.getCartData().getId());
	        //existingPaymentDetail.getCartData().setCardNumber(paymentDetail.getCartData().getCardNumber());
	        //existingPaymentDetail.getCartData().setCVV(paymentDetail.getCartData().getCVV());
	        //existingPaymentDetail.getCartData().setExpiry(paymentDetail.getCartData().getExpiry());
	        
	        existingPaymentDetail.setTransactionId(paymentDetail.getTransactionId());

	        paymentDao.save(existingPaymentDetail);
	    }
	}

	
	public OrderBasePaymentData getPaymentDetails(OrderBasePaymentData paymentDetail) {
		return paymentDao.findByOrderId(paymentDetail.getOrderId());
	}
}
