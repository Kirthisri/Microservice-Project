package com.example.fabonuser.exception;

public class FabonUserExceptions extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String responseCode;
	private String errorMessage;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public FabonUserExceptions(String responseCode, String errorMessage) {
		super();
		this.responseCode = responseCode;
		this.errorMessage = errorMessage;
	}

}
