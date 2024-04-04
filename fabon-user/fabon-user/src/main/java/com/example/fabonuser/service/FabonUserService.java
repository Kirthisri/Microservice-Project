package com.example.fabonuser.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fabonuser.controller.FabonUserController;
import com.example.fabonuser.dao.FabonUsersDao;
import com.example.fabonuser.dto.FabonUserProduct;
import com.example.fabonuser.dto.FabonUsers;

@Service
public class FabonUserService {
	
	public static final Logger log = LoggerFactory.getLogger(FabonUserService.class);
	
	@Autowired
	FabonUsersDao fabonUsersDao;
	
	
	public void saveSingleUserInDB(FabonUsers fabonUsers) {
		try {
			fabonUsersDao.save(fabonUsers);
			log.info("saveSingleUserInDB() - user data is saved");
		} catch (Exception e) {
			log.error("saveSingleUserInDB() - exception while saving data "  +e);		
		}
	}
	
	public List<FabonUsers> getAllUsers(){
		List<FabonUsers> allusers = new ArrayList<>();
		try {
			allusers = fabonUsersDao.findAll();
			log.info("getAllUsers() - user data is fetched");
		}catch(Exception e) {
			log.error("getAllUsers() - exception while fetching data "+e);	
		}
		
		return allusers;
	}
	
	public FabonUsers getUserByEmailId(String emailId){
		FabonUsers userDet = new FabonUsers();
		try {
			userDet = fabonUsersDao.findByEmailId(emailId);
			log.info("getUserByEmailId() - user data is fetched for email: " + emailId);
		}catch(Exception e) {
			log.error("getUserByEmailId() - exception while fetching data by email id: "+emailId + " Exception: " + e);	
		}
		
		return userDet;
	}

}
