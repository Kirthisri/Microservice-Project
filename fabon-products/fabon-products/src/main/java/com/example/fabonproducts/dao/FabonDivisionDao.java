package com.example.fabonproducts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fabonproducts.dto.FabonProductDivision;

@Repository
public interface FabonDivisionDao extends JpaRepository<FabonProductDivision, Long>{

	FabonProductDivision findByDivisionId(String divisionId);

	void deleteByDivisionId(String divisionId);

}
