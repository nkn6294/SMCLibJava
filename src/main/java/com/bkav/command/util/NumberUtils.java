package com.bkav.command.util;

public class NumberUtils {
	public static final String[] INPUT_NUMBERS = { "không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười" };

	public static String textToNumber(String input) {
		String output = input;
		output = textToStringToNumber(output);
		output = text1xToNumber(output);
		output = textx1ToNumber(output);
		output = textx4ToNumber(output);
		output = textx0ToNumber(output);
		output = textxxToNumber(output);
		output = textx00ToNumber(output);
		output = textxxxToNumber(output);
		return output;
	}

	protected static String textToStringToNumber(String input) {
		String output = input;
		for (int index = 1; index < INPUT_NUMBERS.length; index++) {
//			output = output.replaceAll("(\\s?)" + INPUT_NUMBERS[index] + "(\\s?)", "$1" + index + "$2");
			output = output.replaceAll("(\\b)" + INPUT_NUMBERS[index] + "(\\b)", index + "");
		}
		return output;
	}

	protected static String textxxxToNumber(String input) {
		String patternString = "(\\d)00\\s(\\d\\d)";
		return input.replaceAll(patternString, "$1$2");
	}

	protected static String textxxToNumber(String input) {
		String patternString = "(\\d)\\s(\\d)$";
		return input.replaceAll(patternString, "$1$2");
	}

	protected static String text1xToNumber(String input) {
		String patternString = "10\\s(\\d)";
		return input.replaceAll(patternString, "1$1");
	}
	protected static String textx0ToNumber(String input) {
		String patternString = "([2-9])\\smươi";
		return input.replaceAll(patternString, "$10");
	}

	protected static String textx00ToNumber(String input) {
		String patternString = "(\\d)\\străm";
		return input.replaceAll(patternString, "$100");
	}

	protected static String textx1ToNumber(String input) {
		String patternString = "(\\d)\\s(mươi\\s+)?mốt";
		return input.replaceAll(patternString, "$11");
	}
	
	protected static String textx4ToNumber(String input) {
		String patternString = "(\\d)\\s(mươi\\s+)?tư";
		return input.replaceAll(patternString, "$14");
	}
}
