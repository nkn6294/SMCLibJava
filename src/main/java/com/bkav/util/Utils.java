package com.bkav.util;

public class Utils {

	public static boolean isNull(Object object) {
		return object == null;
	}
	public static boolean isNotNull(Object object) {
		return object != null;
	}
	
	public static boolean isStringNullOrEmpty(String input) {
		if (input == null) {
			return true;
		}
		return input.isEmpty();
	}
}
