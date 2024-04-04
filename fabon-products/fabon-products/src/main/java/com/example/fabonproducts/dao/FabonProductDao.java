package com.example.fabonproducts.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fabonproducts.dto.FabonProducts;

@Repository
public interface FabonProductDao extends JpaRepository<FabonProducts, Long>{

	FabonProducts findByProductId(String productId);

	void deleteByProductId(String productId);

}
