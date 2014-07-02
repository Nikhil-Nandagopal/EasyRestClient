package com.easydroid.utils;


public enum ResponseCodes {

	OK(200),
	CONNECTION_TIMEOUT(100),
	UNKNOWN(500),
	MANUALLY_CANCELLED(300),
    JSON_PARSE_EXCEPTION(301),
    IO_EXCEPTION(302);
	
	private int value;

	private ResponseCodes (int value){
		this.value = value;
	}

	public int getResponseValue(){
		return this.value;
	}

	public static ResponseCodes getEnum(int val){
		for(ResponseCodes responseCode : ResponseCodes.values()){
			if(responseCode.value == val){
				return responseCode;
			}
		}
		return null;
	}
	
}
