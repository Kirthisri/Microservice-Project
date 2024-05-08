package com.example.fabonpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabonpayment.dto.OrderBasePaymentData;
import com.example.fabonpayment.dto.PaymentStatus;
import com.example.fabonpayment.service.FabonPaymentService;

@RestController
@RequestMapping("/userPayment")
public class FabonPaymentController {
	
	
	@Autowired
	FabonPaymentService paymentService;

	@PostMapping(value = "/doPayment")
	public OrderBasePaymentData doPayment(@RequestBody OrderBasePaymentData paymentDetail) throws Exception {
		
		OrderBasePaymentData paymentData = paymentService.getPaymentDetails(paymentDetail);
		
		//validate given data
		boolean proceedPayment = paymentService.validatePayment(paymentDetail);
		if(!proceedPayment) {
			throw new Exception("Validation failed - please check the given data");
		}
		else {
			try {
							
				if(null != paymentData) {
					paymentDetail = paymentData;
				}
				
				//save the paymentData
				paymentDetail.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED.getValue());
				paymentService.savePaymentDetails(paymentDetail);
			}
			catch(Exception e) {
				paymentDetail.setPaymentStatus(PaymentStatus.PAYMENT_FAILED.getValue());
				paymentService.savePaymentDetails(paymentDetail);
				
				e.printStackTrace();
				System.out.println("Failed to save data");				
			}
		}
		
		return paymentService.getPaymentDetails(paymentDetail);
	}
	
	@PutMapping(value = "/retryPayment")
	public OrderBasePaymentData retryPayment(@RequestBody OrderBasePaymentData paymentDetail) {

		try {
			paymentDetail.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED.getValue());
			// save the paymentData
			paymentService.updatePaymentDetails(paymentDetail);
		} catch (Exception e) {
			paymentDetail.setPaymentStatus(PaymentStatus.PAYMENT_NOT_DONE.getValue());
			paymentService.updatePaymentDetails(paymentDetail);
			e.printStackTrace();
			System.out.println("Failed to save data");
		}

		return paymentService.getPaymentDetails(paymentDetail);
	}
}
