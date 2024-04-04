package com.example.fabonproducts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fabonproducts.dto.FabonProductCategory;

@Repository
public interface FabonCategoryDao extends JpaRepository<FabonProductCategory, Long>{

	FabonProductCategory findByCategoryId(String categoryId);

	void deleteByCategoryId(String categoryId);

}
