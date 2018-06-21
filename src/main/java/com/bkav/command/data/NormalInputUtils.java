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
    	//TODO replace _,.;:, 
    	return string.replaceAll("\\s+", " ");
    }
	public static String textToUnit(String input) {
		String output = input;
		output = celsiusToText(output);
		output = percentToText(output);
		return output;
	}
	protected static String celsiusToText(String input) {
		String patternString = "\\b(\\d+)\\sđộ(\\s[cC])?\\b";
		return input.replaceAll(patternString, "$1" + CELSIUS_UNIT);
	}
	
	protected static String percentToText(String input) {
		String patternString = "\\b(\\d+)\\sphần\\străm\\b";
		return input.replaceAll(patternString, "$1" + PERCENT_UNIT);
	}
}
