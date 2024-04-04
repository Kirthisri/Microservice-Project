package com.example.fabonuser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fabonuser.dto.FabonUsers;

@Repository
public interface FabonUsersDao extends JpaRepository<FabonUsers, Long>{

	//FabonUsers save(FabonUsers fabonUsers);

	FabonUsers findByEmailId(String emailId);

}
