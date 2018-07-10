package com.bkav.command.model.time;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.model.ParseStringModel;

public class ShortDateModel extends ParseStringModel<Object> {

	public ShortDateModel() {
		super();
		this.modelName = "SHORT_DATE_MODEL";
	}
	
	public static final String DATE_REGEX_PATTERN = "((\\d{1,2})-(\\d{1,2})-(\\d{1,2}))";
	public static final String DATE_CONTEXT_REGEX_PATTERN = "(_([dwmy])(\\+)(\\d))";
	protected static Pattern datePattern = Pattern.compile(DATE_REGEX_PATTERN);
	protected static Pattern dateContextPattern = Pattern.compile(DATE_CONTEXT_REGEX_PATTERN);

	@Override
	protected boolean preWordFilter(String word) {
		if (!super.preWordFilter(word)) {
			return false;
		}
		return word.startsWith("_date(");
	}
	
	@Override
	protected String getStringData(String word) {
		return word.replaceFirst("_date\\((.+)\\)", "$1");
	}
	
	@Override
	protected Object createData(String word) {
		try {
			Matcher matcher = datePattern.matcher(word);
			if (matcher.find()) {
				int year = Integer.parseInt(matcher.group(1));
				int month = Integer.parseInt(matcher.group(2));
				int dayOfMonth = Integer.parseInt(matcher.group(3));
				if (year == 0) {
					return MonthDay.of(month, dayOfMonth);
				} else {
					return LocalDate.of(year, month, dayOfMonth);
				}
			}
			matcher = dateContextPattern.matcher(word);
			if (matcher.find()) {
				String param = matcher.group(2);
				int value = Integer.parseInt(matcher.group(4));
				if ("d".equals(param)) {
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
