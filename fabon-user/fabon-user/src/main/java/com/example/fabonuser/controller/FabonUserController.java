package com.example.fabonuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabonuser.dto.FabonUsers;
import com.example.fabonuser.exception.FabonUserExceptions;
import com.example.fabonuser.service.FabonUserService;
import com.example.fabonuser.util.ExceptionConstants;
import com.example.fabonuser.util.FabonUserStatusEnums;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class FabonUserController {
	
	public static final Logger log = LoggerFactory.getLogger(FabonUserController.class);
	
	@Autowired
	FabonUserService fabonUserService;	
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemp;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping(value="/signup")
	public FabonUsers saveUserData(@RequestBody FabonUsers users) {	
		log.info("in FabonUserController layer - /user/signup - saving General user data to DB");		
		fabonUserService.saveSingleUserInDB(users);
		
		//converting the request object to string
		String jsonUsers = "";
	    try {
	    	jsonUsers = objectMapper.writeValueAsString(users);
	    } catch (JsonProcessingException e) {
	        // Handle serialization error
	       log.error("FabonUserController: API - /signup  Error: " + e);
	    }
		
		//sending the general details of user to kafka
	    log.info("in FabonUserController layer - /user/signup - sending uesr details to kafka under USER-TOPIC");
		kafkaTemp.send("USER-TOPIC", "NEW_USER", jsonUsers);
		
		return users;
	}
	
	@PostMapping(value="/forgotPassword")
	public String sendEmailForPwdChange(@RequestBody FabonUsers users) {
		if(null != users.getEmailId()) {
			log.info("in FabonUserController layer - /user/forgotPassword - sending email to the user for pwd change");
			return "Email sent to user";
		}
		else {
			log.info("Provide Registered email Id");
			return "Provide Registered email Id";
		}
		
	}
	
	@PutMapping(value="/changePassword/{emailId}")
	public FabonUsers changeUserLoginPassword(@PathVariable String emailId) throws FabonUserExceptions {
		FabonUsers userDetail = fabonUserService.getUserByEmailId(emailId);
		
		if(null != userDetail) {
			userDetail.setPassword(emailId);
			userDetail.setConfirmPassword(emailId);
			fabonUserService.saveSingleUserInDB(userDetail);
			log.info("in FabonUserController layer - /user/changePassword - new password is saved to DB");
			
			//converting the request object to string
			String jsonUsers = "";
		    try {
		    	jsonUsers = objectMapper.writeValueAsString(userDetail);
		    } catch (JsonProcessingException e) {
		        // Handle serialization error
		       log.error("FabonUserController: API - /signup  Error: " + e);
		    }
			kafkaTemp.send("USER-TOPIC", FabonUserStatusEnums.EXISTING_USER.toString(), jsonUsers);
			log.info("in FabonUserController layer - /user/changePassword - updated user data is sent to kafka - USER-TOPIC");
			
		}
		else {
			throw new FabonUserExceptions(ExceptionConstants.WRONG_EMAIL_ID,
					ExceptionConstants.PROVIDED_WRONG_EMAIL_ID);
		}
		
		return userDetail;
	}
}
