package com.example.fabonreader.dto;

import org.springframework.stereotype.Component;

@Component
public class FabonExceptionMessage {
	private String errorCode;
	private String errorMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	// Builder class
    public static class Builder {
        private String errorCode;
        private String errorMessage;

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public FabonExceptionMessage build() {
        	FabonExceptionMessage errorDetails = new FabonExceptionMessage();
            errorDetails.errorCode = this.errorCode;
            errorDetails.errorMessage = this.errorMessage;
            return errorDetails;
        }
    }
}
