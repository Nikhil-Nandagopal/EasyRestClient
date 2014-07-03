package com.easydroid.utils;


public enum ResponseCodes {

	OK(200),
	CONNECTION_TIMEOUT(50),
	UNKNOWN(500),
	NOT_FOUND(404),
	MANUALLY_CANCELLED(51),
    JSON_PARSE_EXCEPTION(52),
    IO_EXCEPTION(53);
	
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
