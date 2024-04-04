package com.example.faboncart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.faboncart.dto.FabonUserCart;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface FabonCartDao extends JpaRepository<FabonUserCart, Long>{

	FabonUserCart findByCartIdAndCartProductId(String cartId, String cartProductId);
	
	List<FabonUserCart> findByCartId(String cartId);

	void deleteByCartIdAndCartProductId(String cartId, String cartProductId);

	//@Modifying
	//@Query("UPDATE CartDetail c SET c.orderPlaced = true WHERE c.cartId = ?1")
	//void updateOrderPlacedTrueByCartId(String cartId);

	@Modifying
	@Query("UPDATE FabonUserCart c SET c.quantity = :quantity WHERE c.cartId = :cartId")
	void updateQuantityByCartId(@Param("quantity") int quantity, @Param("cartId") String cartId);

	
//	@Modifying
//	@Query("UPDATE CartDetail c SET c.orderPlaced = true WHERE c.emailId = :emailId")
//	void updateOrderPlacedByEmailId(@Param("emailId") String emailId);


}
