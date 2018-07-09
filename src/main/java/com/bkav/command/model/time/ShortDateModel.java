package com.bkav.command.model.time;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.model.ParseStringModel;

public class ShortDateModel extends ParseStringModel<LocalDate> {

	public ShortDateModel() {
		super();
		this.modelName = "SHORT_DATE_MODEL";
	}
	
	public static final String DATE_REGEX_PATTERN = "((\\d{1,2})-(\\d{1,2})-(\\d{1,2}))";
	protected static Pattern datePattern = Pattern.compile(DATE_REGEX_PATTERN);

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
	protected LocalDate createData(String word) {
		try {
			Matcher matcher = datePattern.matcher(word);
			if (!matcher.find()) {
				throw new Exception();
			}
			int year = Integer.parseInt(matcher.group(1));
			int month = Integer.parseInt(matcher.group(2));
			int dayOfMonth = Integer.parseInt(matcher.group(3));
			return LocalDate.of(year, month, dayOfMonth);
		} catch (Exception ex) {
			return null;
		}
	}
}
