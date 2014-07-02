package com.easydroid.domains;

import com.easydroid.utils.ResponseCodes;


public class ResponseContainer {
	
	protected transient ResponseCodes responseCode;
	
	public ResponseCodes getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(ResponseCodes responseCode) {
		this.responseCode = responseCode;
	}
	
}
