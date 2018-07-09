package com.bkav.command.model.value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.data.time.ValueUnit;
import com.bkav.command.model.ParseStringModel;

public class ValueModel extends ParseStringModel<ValueUnit> {

	public ValueModel() {
		super();
		this.modelName = "VALUE_MODEL";
	}

	protected static final String REGEX_PATTERN = "(\\D)?(\\d+)(.+)";
	protected static Pattern pattern = Pattern.compile(REGEX_PATTERN);

	@Override
	protected boolean preWordFilter(String word) {
		if (!super.preWordFilter(word)) {
			return false;
		}
		return word.startsWith("_value(");
	}

	@Override
	protected String getStringData(String word) {
		return word.replaceFirst("_value\\((.+)\\)", "$1");
	}

	@Override
	protected ValueUnit createData(String word) {
		try {
			Matcher matcher = pattern.matcher(word);
			if (!matcher.find()) {
				throw new Exception();
			}
			int value = Integer.parseInt(matcher.group(2));
			String unit = matcher.group(3);
			return new ValueUnit(value, unit);
		} catch (Exception ex) {
			return null;
		}
	}
}