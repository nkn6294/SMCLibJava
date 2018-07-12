package com.bkav.command.model.time;

import static com.bkav.command.util.StringUtil.textProcessByRegex;

public class TimeUtils {
	public static String timeToNormal(String input) {
		String output = input;
		output = normalHourUnit(output);
		output = normalMinuteUnit(output);
		output = normalSecondUnit(output);
		output = longTimeToShort(output);
		output = longTimeOnlyHourToShort(output);
		output = longTimeOnlyMinuteToShort(output);
		output = normalAMPM(output);
		output = timeInDayToAMPM(output);
		output = timeAMToShort(output);
		output = timePMToShort(output);
		output = normalRelativeTime(output);
		output = normalAtTime(output);
		output = boundTime(output);
		return output;
	}

	protected static String boundTime(String input) {
		String patternString = "(\\+?\\d{1,2}(m|(:\\d{1,2}))a?)";
		return input.replaceAll(patternString, "_time($1)");
	}
	
	protected static String normalHourUnit(String input) {
		String patternString = "\\b(\\d+)\\s*((giờ)|(tiếng))\\b";
		return input.replaceAll(patternString, "$1h");
	}

	protected static String normalMinuteUnit(String input) {
		String patternString = "\\b(\\d+)\\s*phút\\b";
		return input.replaceAll(patternString, "$1m");
	}

	protected static String normalSecondUnit(String input) {
		String patternString = "\\b(\\d+)\\s*giây\\b";
		return input.replaceAll(patternString, "$1s");
	}
	
	protected static String normalRelativeTime(String input) {
		String patternString = "(\\bsau\\s)?(\\d{1,2}:\\d{1,2})\\snữa\\b";
		return input.replaceAll(patternString, "+$2");
	}
	protected static String normalAtTime(String input) {
		String patternString = "(\\blúc\\s)?(\\d{1,2}:\\d{1,2}a?)\\b";
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
			} else if ("trưa".equals(timeInDay)) {
				int hour = Integer.parseInt(matcher.group(1));
				if (hour >= 1 && hour <= 3) {
					builder.append("pm");
				} else {
					builder.append("am");
				} 
			} else if("đêm".equals(timeInDay)) {
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
	}
	protected static String timeAMToShort(String input) {
		String patternString = "\\b(\\d+)(:\\d{2})?am\\b";
		return textProcessByRegex(input, patternString, (matcher, builder) -> builder.append(matcher.group(1))
				.append(matcher.group(2) == null ? ":00" : matcher.group(2)).append("a"));
	}

	protected static String timePMToShort(String input) {
		String patternString = "\\b(\\d+)(:\\d{2})?pm\\b";
		return textProcessByRegex(input, patternString,
				(matcher, builder) -> builder.append(Integer.parseInt(matcher.group(1)) % 12 + 12)
						.append(matcher.group(2) == null ? ":00" : matcher.group(2)));
	}

	protected static String longTimeToShort(String input) {
		String patternString = "\\b(\\d+)h\\s(\\d+)m?\\b";
		return input.replaceAll(patternString, "$1:$2");
	}

	protected static String longTimeOnlyHourToShort(String input) {
		String patternString = "\\b(\\d+)h\\b";
		return input.replaceAll(patternString, "$1:00");
	}
	protected static String longTimeOnlyMinuteToShort(String input) {
		String patternString = "\\b(\\d+)m\\b";
		return input.replaceAll(patternString, "00:$1");
	}
	private TimeUtils() {
	};
}
