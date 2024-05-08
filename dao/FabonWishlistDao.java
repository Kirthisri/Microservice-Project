package com.example.fabonwishlist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.fabonwishlist.dto.FabonUserWishlist;


//@Transactional
//@Repository
public interface FabonWishlistDao extends JpaRepository<FabonUserWishlist, Long>{

	List<FabonUserWishlist> findByEmailId(String emailId);

	void deleteByEmailAndProductId(String emailId, String cartProductId);
	
	@Modifying
    @Query("UPDATE WishlistDetail c SET c.inStock = true WHERE c.wishlistProductId = :wishlistProductId")
    void updateInStockByWishlistProductId(@Param("wishlistProductId") String wishlistProductId);

	void deleteByWishlistProductId(String wishlistProductId);

	List<FabonUserWishlist> findByWishlistProductId(String wishlistProductId);

}
