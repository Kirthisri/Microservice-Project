package com.example.fabonpayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.fabonpayment.dto.OrderBasePaymentData;

@Repository
public interface FabonPaymentDao extends JpaRepository<OrderBasePaymentData, Long>{

	OrderBasePaymentData findByOrderId(String orderId);

}
