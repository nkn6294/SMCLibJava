package com.bkav.command.model.time;

import static com.bkav.command.util.StringUtil.textProcessByRegex;

public class DateUtils {
	public static String dateToNormal(String input) {
		String output = input;
		output = longDateFormatToShort(output);
		output = normalDayInMonthUnit(output);
		output = normalMonthUnit(output);
		output = normalYearUnit(output);
		
		output = longDateToShort(output);
		output = longDateWithoutYear(output);
		output = longDateOnlyDayOnMonth(output);
		
		output = normalDayOfWeek(output);
		output = normalCurrentWeek(output);
		output = normalNextWeek(output);
		output = normalWeekInMonth(output);
		output = normalNextDay(output);
		output = normalCurrentDay(output);
		output = boundDate(output);
		return output;
	}
	
	protected static String boundDate(String input) {
		String output = input;
		String longDatePatternString = "\\b(\\d{1,2}-\\d{1,2}-\\d{4})\\b";
		output = output.replaceAll(longDatePatternString, "_date($1)");
		return output;
	}
	protected static String longDateFormatToShort(String input) {
		String patternString = "\\b(ngày\\s)?((\\d{1,2})[\\-/_](\\d{1,2})[\\-/_](\\d{4}))\\b";
		return input.replaceAll(patternString, "$3-$4-$5");
	}
	protected static String longDateToShort(String input) {
		String patternString = "\\b_d(\\d+)\\s_m(\\d+)\\s_y(\\d+)\\b";
		return input.replaceAll(patternString, "$1-$2-$3");
	}

	protected static String longDateWithoutYear(String input) {
		String patternString = "\\b(_d)?(\\d+)\\s_m(\\d+)\\b";
		return input.replaceAll(patternString, "$2-$3-0000");
	}
	protected static String longDateOnlyDayOnMonth(String input) {
		String patternString = "\\b_d(\\d+)\\b";
		return input.replaceAll(patternString, "$1-00-0000");
	}

	protected static String normalDayInMonthUnit(String input) {
		String patternString = "\\bngày\\s(\\d+)\\b";
		return input.replaceAll(patternString, "_d$1");
	}

	protected static String normalMonthUnit(String input) {
		String patternString = "\\btháng\\s(\\d+)\\b";
		return input.replaceAll(patternString, "_m$1");
	}

	protected static String normalYearUnit(String input) {
		String patternString = "\\bnăm\\s(\\d+)\\b";
		return input.replaceAll(patternString, "_y$1");
	}
	protected static String normalDayOfWeek(String input) {
		String patternString = "\\b((thứ\\s([2-7]|(tư))|(chủ\\snhật)))\\b";
		return textProcessByRegex(input, patternString, (matcher, builder) -> {
			int stt = 0;
			if (matcher.group(5) != null) {
				stt = 8;
			} else {
				if ("tư".equals(matcher.group(3))) {
					stt = 4;
				} else {
					stt = Integer.parseInt(matcher.group(3));					
				}
			}
			return builder.append("_e").append(stt);
		});
	}
	protected static String normalCurrentDay(String input) {
		String patternString = "\\bhôm\\snay\\b";
		return input.replaceAll(patternString, "_d+0");
	}
	protected static String normalCurrentWeek(String input) {
		String patternString = "\\btuần\\snày\\b";
		return input.replaceAll(patternString, "_w+0");
	}
	protected static String normalNextWeek(String input) {
		String patternString = "\\btuần\\s(sau)|(tới)\\b";
		return input.replaceAll(patternString, "_w+1");
	}
	protected static String normalNextDay(String input) {
		String patternString = "\\bngày\\s(mai)|(tới)\\b";
		return input.replaceAll(patternString, "_d+1");
	}
	protected static String normalWeekInMonth(String input) {
		String patternString = "\\btuần\\s(\\d)\\s_m(\\d+)\\b";
		return input.replaceAll(patternString, "_w\\#$1_m$2");
	}
	private DateUtils() {
	};
}
