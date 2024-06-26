package com.example.fabonreader.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fabonreader.dto.FabonProducts;
@Repository
public interface FabonUserProductDao extends MongoRepository<FabonProducts, Long>{
	
	Optional<FabonProducts> findByProductId(String productId);
	
	//@Query("select p from FabonProducts p where p.productCategoryId = :productCategoryId and p.productDivisionId = :productDivisionId")
	//@Query(value="{'productCategoryId': :productCategoryId, 'productDivisionId': :productDivisionId}"
	//		, name="findAllProductsByproductCategoryIdAndproductDivisionId")
	//@Query(value = "{'productCategoryId': :productCategoryId, 'productDivisionId': :productDivisionId}")

	//List<FabonProducts> findProductsByCategoryIdAndDivisionId(@Param("productCategoryId") String productCategoryId, 
	//		@Param("productDivisionId") String productDivisionId);
	
	@Query("{'divisionId': ?0}")
	List<FabonProducts> findByDivisionId(String divisionId);
}
