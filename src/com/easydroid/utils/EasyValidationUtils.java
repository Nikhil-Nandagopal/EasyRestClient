package com.easydroid.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyValidationUtils {

	private static Pattern pattern;
	private static Matcher matcher;
	private static final String EMAIL_REGEX = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,4})$";
	private static final String MOBILE_REGEX = "^[0-9]{10}$";
	private static final String NUMBER_REGEX = "^[0-9]*$";

	public static boolean validateEmailID(String email) {
		pattern = Pattern.compile(EMAIL_REGEX);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validateNumber(String text) {
        pattern = Pattern.compile(NUMBER_REGEX);
        matcher = pattern.matcher(text);
        return matcher.matches();
    }
	
	public static boolean validateMobileNo(String text) {
		pattern = Pattern.compile(MOBILE_REGEX);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
}
