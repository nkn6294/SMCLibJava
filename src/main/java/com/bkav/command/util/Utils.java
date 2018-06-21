package com.bkav.command.util;

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
	
	public static String getShortString(String content, int maxLength) {
		if (maxLength <= 0) {
			return "";
		}
		if (content == null) return null;
		int length = content.length();
		if (length < maxLength) return content;
		return String.format("%s...%s", content.substring(0, maxLength / 2), content.substring(length - maxLength / 2, length));
	}   
	public static String getShortString(String content) {
		return getShortString(content, 10);
	}   
}
