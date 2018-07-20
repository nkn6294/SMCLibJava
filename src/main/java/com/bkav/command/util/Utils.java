package com.bkav.command.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

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
	public static String getJSONStringWithoutComment(Reader reader) throws IOException {
		return getJSONStringWithoutComment(reader, '#');
	}
	public static String getJSONStringWithoutComment(Reader reader, char commentSign) throws IOException {
		if (reader == null) {
			throw new NullPointerException(); 
		}
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferReader = null;
		if (reader instanceof BufferedReader) {
			bufferReader = (BufferedReader) reader;
		} else {
			bufferReader = new BufferedReader(reader);
		}
		String line = null;
		
		while ((line = bufferReader.readLine()) != null) {
			line = line.replaceAll("\\s*" + commentSign, commentSign + "");//Read JSON file support comment '//' 
			line = line.replaceAll(commentSign + ".*", "");
			if (line.isEmpty()) {
				continue;
			}
			builder.append(line);
		}
		return builder.toString();
	}
}
