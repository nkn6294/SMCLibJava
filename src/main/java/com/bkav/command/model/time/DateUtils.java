package com.bkav.command.model.time;

import static com.bkav.command.util.StringUtil.textProcessByRegex;

import java.util.regex.Matcher;

import com.bkav.command.common.Function2;

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
		// output = normalWeekInMonth(output);
		output = normalNextDay(output);
		output = normalCurrentDay(output);
		output = normalTimeRepeat(output);
		output = boundDate(output);
		return output;
	}

	protected static String boundDate(String input) {
		String output = input;
		String longDatePatternString = "\\b(\\d{1,2}-\\d{1,2}-\\d{4})\\b";
		output = output.replaceAll(longDatePatternString, "_date_$1_");

		String timeContextPatternString = "\\b(_([edwmy])(\\+)((\\d\\b)|\\*))";
		output = output.replaceAll(timeContextPatternString, "_date_$1_");
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
		return textProcessByRegex(input, patternString, new Function2<Matcher, StringBuilder, StringBuilder>() {
			public StringBuilder apply(Matcher matcher, StringBuilder builder) {
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
				return builder.append("_e+").append(stt);
			}

		});
	}

	protected static String normalTimeRepeat(String input) {
		String patternString = "\\b((hàng)|(mỗi))\\s((ngày)|(tuần)|(tháng)|(năm))\\b";
		return textProcessByRegex(input, patternString, new Function2<Matcher, StringBuilder, StringBuilder>() {
			public StringBuilder apply(Matcher matcher, StringBuilder builder) {
				try {
					String param = null;
					String unit = matcher.group(4);// ngay|tuan|thang|nam
					if ("ngày".equals(unit)) {
						param = "_d";
					} else if ("tuần".equals(unit)) {
						param = "_w";
					} else if ("tháng".equals(unit)) {
						param = "_m";
					} else if ("năm".equals(unit)) {
						param = "_y";
					}
					if (param == null) {
						return builder.append(input);
					}
					return builder.append(param).append("+*");
				} catch (Exception ex) {
					return builder.append(input);
				}
			}
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
		String patternString = "\\btuần\\s((sau)|(tới))\\b";
		return input.replaceAll(patternString, "_w+1");
	}

	protected static String normalNextDay(String input) {
		String patternString = "\\bngày\\s((mai)|(tới))\\b";
		return input.replaceAll(patternString, "_d+1");
	}

	// protected static String normalWeekInMonth(String input) {
	// String patternString = "\\btuần\\s(\\d)\\s_m(\\d+)\\b";
	// return input.replaceAll(patternString, "_w\\#$1_m$2");
	// }

	private DateUtils() {
	};
}
