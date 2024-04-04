package com.example.fabonreader.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseData {

	private Object data;
	private String responseCode;
	private String responseMessage;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	// Builder class
    public static class Builder {
        private Object data;
        private String responseCode;
        private String responseMessage;

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder responseCode(String responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder responseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public ResponseData build() {
            ResponseData responseData = new ResponseData();
            responseData.data = this.data;
            responseData.responseCode = this.responseCode;
            responseData.responseMessage = this.responseMessage;
            return responseData;
        }
    }
	
}
