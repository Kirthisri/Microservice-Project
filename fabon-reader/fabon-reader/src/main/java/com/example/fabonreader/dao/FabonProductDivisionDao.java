package com.example.fabonreader.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fabonreader.dto.FabonProductDivision;


@Repository
public interface FabonProductDivisionDao extends MongoRepository<FabonProductDivision, Long>{

	@Query("{'categoryId': :categoryId}")
	List<FabonProductDivision> findByCategoryId(@Param("categoryId") String categoryId);


}
