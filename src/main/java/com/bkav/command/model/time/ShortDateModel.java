package com.bkav.command.model.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.data.time.DayOfMonth;
import com.bkav.command.data.time.TimeRepeat;
import com.bkav.command.data.time.TimeRepeat.TimeRepeatType;
import com.bkav.command.model.ParseStringModel;

public class ShortDateModel extends ParseStringModel<Object> {

	public ShortDateModel() {
		super();
		this.modelName = "SHORT_DATE_MODEL";
	}
	
	public static final String DATE_REGEX_PATTERN = "((\\d{1,2})-(\\d{1,2})-(\\d{4}))";
//	public static final String DATE_CONTEXT_REGEX_PATTERN = "(_([edwmy])(\\+)(\\d))";
	public static final String DATE_CONTEXT_REGEX_PATTERN = "(_([edwmy])(\\+)((\\d\\b)|\\*))";
	protected static Pattern datePattern = Pattern.compile(DATE_REGEX_PATTERN);
	protected static Pattern dateContextPattern = Pattern.compile(DATE_CONTEXT_REGEX_PATTERN);

	@Override
	protected boolean preWordFilter(String word) {
		if (!super.preWordFilter(word)) {
			return false;
		}
//		return word.startsWith("_date(");
		return word.startsWith("_date_");
	}
	
	@Override
	protected String getStringData(String word) {
		return word.replaceAll("_date_(.+)_", "$1");
	}
	
	@Override
	protected Object createData(String word) {
		try {
			Matcher matcher = datePattern.matcher(word);
			if (matcher.find()) {
				int dayOfMonth = Integer.parseInt(matcher.group(2));
				int month = Integer.parseInt(matcher.group(3));
				int year = Integer.parseInt(matcher.group(4));
				if (year == 0) {
					if (month == 0) {
						return DayOfMonth.of(dayOfMonth);
					}
					return MonthDay.of(month, dayOfMonth);
				} else {
					return LocalDate.of(year, month, dayOfMonth);
				}
			}
			matcher = dateContextPattern.matcher(word);
			if (matcher.find()) {
				String param = matcher.group(2);
				String valueString = matcher.group(4);
				if ("*".equals(valueString)) {
					if ("d".equals(param)) {
						return TimeRepeat.of(TimeRepeatType.DAILY);
					} else if ("w".equals(param)) {
						return TimeRepeat.of(TimeRepeatType.WEEKLY);
					} else if ("m".equals(param)) {
						return TimeRepeat.of(TimeRepeatType.MONTHLY);
					} else if ("y".equals(param)) {
						return TimeRepeat.of(TimeRepeatType.YEARLY);
					}
					throw new Exception();
				}
				int value = Integer.parseInt(valueString);
				if ("e".equals(param)) {
					return DayOfWeek.of(value - 1);
				} else if ("d".equals(param)) {
					return Period.ofDays(value);
				} else if ("w".equals(param)) {
					return Period.ofWeeks(value);
				} else if ("m".equals(param)) {
					return Period.ofMonths(value);
				} else if ("y".equals(param)) {
					return Period.ofYears(value);
				} 
			}
			throw new Exception();
		} catch (Exception ex) {
			return null;
		}
	}
}
