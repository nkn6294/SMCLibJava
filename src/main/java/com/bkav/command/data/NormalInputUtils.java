package com.bkav.command.data;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class NormalInputUtils {
	public static final String CELSIUS_UNIT = "\u2103";
	public static final String PERCENT_UNIT = "%";

	public static String deAccentConvert(String input) {
		String nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll("đ", "d");
	}

    public static String normalInputSplitChar(String string) {
    	//TODO replace _,.;:...
    	return string;
    }
	public static String textToUnit(String input) {
		String output = input;
		output = celsiusToText(output);
		output = percentToText(output);
		return output;
	}

	public static String textToNumber(String input) {
		String output = input;
		String[] inputNumbers = { "không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười" };
		for (int index = 1; index < inputNumbers.length; index++) {
			output = output.replaceAll(inputNumbers[index], "" + index);
		}
		output = text1xToNumber(output);
		output = text1xToNumber2(output);
		output = textx1ToNumber2(output);
		output = textx4ToNumber2(output);
		output = textx1ToNumber(output);
		output = textx4ToNumber(output);
		output = textx0ToNumber(output);
		output = textxxToNumber(output);
		output = textx00ToNumber(output);
		output = textxxxToNumber(output);
		return output;
	}

	//////////////////////
	protected static String celsiusToText(String input) {
		String patternString = "\\b(\\d+)\\s+độ\\s+c\\b";
		return input.replaceAll(patternString, "$1" + CELSIUS_UNIT);
	}

	protected static String percentToText(String input) {
		String patternString = "\\b(\\d+)\\s+phần\\s+trăm\\b";
		return input.replaceAll(patternString, "$1" + PERCENT_UNIT);
	}

	// textToNumber
	protected static String textToNumber2(String input) {
		String patternString = "((\\d)\\s*trăm\\s*(\\d))+";
		return input.replaceAll(patternString, "$2$3");
	}

	protected static String textxxxToNumber(String input) {
		String patternString = "((\\d)00\\s*(\\d\\d))+";
		return input.replaceAll(patternString, "$2$3");
	}

	protected static String textxxToNumber(String input) {
		String patternString = "((\\d)\\s*(\\d))+";
		return input.replaceAll(patternString, "$2$3");
	}

	protected static String text1xToNumber(String input) {
		String patternString = "(mười\\s*(\\d))+";
		return input.replaceAll(patternString, "1$2");
	}

	protected static String text1xToNumber2(String input) {
		String patternString = "(10\\s*(\\d))+";
		return input.replaceAll(patternString, "1$2");
	}

	protected static String textx0ToNumber(String input) {
		String patternString = "((\\d)\\s*mươi)+";
		return input.replaceAll(patternString, "$20");
	}

	protected static String textx00ToNumber(String input) {
		String patternString = "((\\d)\\s*trăm)+";
		return input.replaceAll(patternString, "$200");
	}

	protected static String textx1ToNumber(String input) {
		String patternString = "((\\d)\\s*mốt)+";
		return input.replaceAll(patternString, "$21");
	}

	protected static String textx4ToNumber(String input) {
		String patternString = "((\\d)\\s*tư)+";
		return input.replaceAll(patternString, "$24");
	}

	protected static String textx1ToNumber2(String input) {
		String patternString = "((\\d)\\s*mươi\\s*mốt)+";
		return input.replaceAll(patternString, "$21");
	}

	protected static String textx4ToNumber2(String input) {
		String patternString = "((\\d)\\s*mươi\\s*tư)+";
		return input.replaceAll(patternString, "$24");
	}
}
