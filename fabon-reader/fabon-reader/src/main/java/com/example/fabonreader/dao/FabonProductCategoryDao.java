package com.example.fabonreader.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fabonreader.dto.FabonProductCategory;


@Repository
public interface FabonProductCategoryDao extends MongoRepository<FabonProductCategory, Long>{
	//@Query("select distinct(productCategoryId), categoryName from Products")
	@Query(value = "{}",fields="{'productCategoryId':1, 'categoryName':1}")
	List<FabonProductCategory> findAllCategory();
}
