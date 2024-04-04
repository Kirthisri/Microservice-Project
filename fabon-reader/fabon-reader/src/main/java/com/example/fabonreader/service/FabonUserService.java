package com.example.fabonreader.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fabonreader.dao.FabonUsersDao;
import com.example.fabonreader.dto.FabonUsers;

@Service
public class FabonUserService {
	
	//public static final Logger log = LoggerFactory.getLogger(FabonUserService.class);
	
	@Autowired
	FabonUsersDao fabonUsersDao;
	
	public Optional<FabonUsers> getUserByEmailId(String emailId){
		Optional<FabonUsers> userDet = java.util.Optional.empty();
		try {
			userDet = fabonUsersDao.findByEmailId(emailId);
			//log.info("getUserByEmailId() - user data is fetched for email: " + emailId);
		}catch(Exception e) {
			//log.error("getUserByEmailId() - exception while fetching data by email id: "+emailId + " Exception: " + e);	
		}
		
		return userDet;
	}

	public Optional<FabonUsers> getUserDetailsById(Long userId) {
		return fabonUsersDao.findById(userId);
	}
}
