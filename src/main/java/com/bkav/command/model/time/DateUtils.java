package com.bkav.command.model.time;

import static com.bkav.util.StringUtil.textProcessByRegex;

public class DateUtils {
	public static String dateToNormal(String input) {
		String output = input;
		output = normalDayInMonthUnit(output);
		output = normalMonthUnit(output);
		output = normalYearUnit(output);
		output = normalDayOfWeek(output);
		output = normalCurrentWeek(output);
		output = normalNextWeek(output);
		output = normalWeekInMonth(output);
		
		output = longDateToShort(output);
		output = longDateWithoutYear(output);
		output = longDateOnlyDayOnMonth(output);
		
		
//		output = normalAMPM(output);
//		output = timeInDayToAMPM(output);
//		output = timeAMToShort(output);
//		output = timePMToShort(output);
//		output = normalRelativeTime(output);
//		output = normalAtTime(output);
		
//		SystemManager.logger.info(">" + output);
//		output = boundDate(output);
		return output;
	}
	
	protected static String boundDate(String input) {
		String patternString = "(\\+?\\d{1,2}(m|(:\\d{1,2})))";
		return input.replaceAll(patternString, "_d_($1)");
	}
	
	protected static String normalRelativeTime(String input) {
		String patternString = "(\\bsau\\s+)?((\\d{1,2})(m|(:\\d{1,2})))\\s+nữa\\b";
		return textProcessByRegex(input, patternString, (matcher, builder) -> {
			builder.append("+");
			if ("m".equals(matcher.group(4))) {
				builder.append("00:").append(matcher.group(3));
			} else {
				builder.append(matcher.group(2));
			}
			return builder;
		});
//		return input.replaceAll(patternString, "+$2");
	}
	protected static String normalAtTime(String input) {
		String patternString = "(\\blúc\\s+)?(\\d{1,2}:\\d{1,2})\\b";
		return input.replaceAll(patternString, "$2");
	}
	protected static String normalAMPM(String input) {
		String patternString = "\\b(\\d+)\\s*(a|p)(\\s*\\.?\\s*m)\\b";
		return input.replaceAll(patternString, "$1$2m");
	}
	protected static String timeInDayToAMPM(String input) {
		String patternString = "\\b(\\d{1,2}):(\\d{1,2})\\s*((sáng)|(trưa)|(chiều)|(tối)|(đêm))\\b";
		return textProcessByRegex(input, patternString, (matcher, builder) -> {
			builder.append(matcher.group(1)).append(":").append(matcher.group(2));
			String timeInDay = matcher.group(3);
			if ("sáng".equals(timeInDay)) {
				builder.append("am");
			} if ("trưa".equals(timeInDay)) {
				int hour = Integer.parseInt(matcher.group(1));
				if (hour >= 1 && hour <= 3) {
					builder.append("am");
				} else {
					builder.append("pm");
				} 
			} if("đêm".equals(timeInDay)) {
				int hour = Integer.parseInt(matcher.group(1));
				if (hour >= 1 && hour <= 3) {
					builder.append("am");
				} else {
					builder.append("pm");
				}
			}
			else if ("chiều".equals(timeInDay) || "tối".equals(timeInDay)) {
				builder.append("pm");
			}
			return builder;
		});
//		return input.replaceAll(patternString, "$1am");
	}
	protected static String timeAMToShort(String input) {
		String patternString = "\\b(\\d+)(:\\d{2})?am\\b";
		return textProcessByRegex(input, patternString, (matcher, builder) -> builder.append(matcher.group(1))
				.append(matcher.group(2) == null ? ":00" : matcher.group(2)));
	}

	protected static String timePMToShort(String input) {
		String patternString = "\\b(\\d+)(:\\d{2})?pm\\b";
		return textProcessByRegex(input, patternString,
				(matcher, builder) -> builder.append(Integer.parseInt(matcher.group(1)) % 12 + 12)
						.append(matcher.group(2) == null ? ":00" : matcher.group(2)));
	}

	protected static String longDateToShort(String input) {
		String patternString = "\\b_d(\\d+)\\s+_m(\\d+)\\s+_y(\\d+)\\b";
		return input.replaceAll(patternString, "$1-$2-$3");
	}

	protected static String longDateWithoutYear(String input) {
		String patternString = "\\b(_d)?(\\d+)\\s+_m(\\d+)\\b";
		return input.replaceAll(patternString, "$2-$3-0000");
	}
	protected static String longDateOnlyDayOnMonth(String input) {
		String patternString = "\\b_d(\\d+)\\b";
		return input.replaceAll(patternString, "$1-00-0000");
	}

	protected static String normalDayInMonthUnit(String input) {
		String patternString = "\\bngày\\s+(\\d+)\\b";
		return input.replaceAll(patternString, "_d$1");
	}

	protected static String normalMonthUnit(String input) {
		String patternString = "\\btháng\\s+(\\d+)\\b";
		return input.replaceAll(patternString, "_m$1");
	}

	protected static String normalYearUnit(String input) {
		String patternString = "\\bnăm\\s+(\\d+)\\b";
		return input.replaceAll(patternString, "_y$1");
	}
	protected static String normalDayOfWeek(String input) {
		String patternString = "\\b((thứ\\s+(\\d+))|(chủ\\s+nhật))\\b";
		return textProcessByRegex(input, patternString, (matcher, builder) -> {
			int stt = 0;
			if (matcher.group(4) != null) {
				stt = 8;
			} else {
				stt = Integer.parseInt(matcher.group(3));
			}
			return builder.append("_e").append(stt);
		});
	}
	protected static String normalCurrentWeek(String input) {
		String patternString = "\\btuần\\s+này\\b";
		return input.replaceAll(patternString, "_w+0");
	}
	protected static String normalNextWeek(String input) {
		String patternString = "\\btuần\\s+(sau)|(tới)\\b";
		return input.replaceAll(patternString, "_w+1");
	}
	protected static String normalWeekInMonth(String input) {
		String patternString = "\\btuần\\s+(\\d)\\s+_m(\\d+)\\b";
		return input.replaceAll(patternString, "_w\\$$1_m$2");
	}
	private DateUtils() {
	};
}
