package com.example.fabonreader.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fabonreader.dto.FabonUsers;


@Repository
public interface FabonUsersDao extends MongoRepository<FabonUsers, Long>{

	@Query("{'emailId':?0}")
	Optional<FabonUsers> findByEmailId(String emailId);
}
