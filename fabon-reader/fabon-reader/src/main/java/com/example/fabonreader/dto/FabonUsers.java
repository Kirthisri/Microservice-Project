package com.example.fabonreader.dto;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Component
//@Entity
//@Table(name="userDetails")
@Document(collection="FabonUsers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabonUsers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@JsonProperty ("id") 
	//@JsonDeserialize(using = LongDeserializer.class)
	//private Long id;
	
	@JsonProperty("fullname")
    private String fullname;
	
	@JsonProperty("place")
    private String place;
	
	@JsonProperty("emailId")
    private String emailId;
	
	@JsonProperty("phnNum")
    private String phnNum;
	
	@JsonProperty("password")
    private String password;
	
	@JsonProperty("gender")
    private String gender;
	
	@JsonProperty("confirmPassword")
    private String confirmPassword;
	
	private FabonExceptionMessage exceptionMessage;
	
	public FabonExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(FabonExceptionMessage exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhnNum() {
		return phnNum;
	}
	public void setPhnNum(String phnNum) {
		this.phnNum = phnNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "FabonUsers [fullname=" + fullname + ", place=" + place + ", emailId=" + emailId
				+ ", phnNum=" + phnNum + ", password=" + password + ", gender=" + gender + ", confirmPassword="
				+ confirmPassword + "]";
	}
	public FabonUsers(Long id, String fullname, String place, String emailId, String phnNum, String password,
			String gender, String confirmPassword) {
		super();
		this.fullname = fullname;
		this.place = place;
		this.emailId = emailId;
		this.phnNum = phnNum;
		this.password = password;
		this.gender = gender;
		this.confirmPassword = confirmPassword;
	}
	public FabonUsers() {
		super();
	}
	
}
