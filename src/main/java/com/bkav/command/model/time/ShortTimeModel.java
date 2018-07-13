package com.bkav.command.model.time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.bkav.command.data.time.TimeValue.DayContext;
import com.bkav.command.model.ParseStringModel;

public class ShortTimeModel extends ParseStringModel<Object> {// extends AbstractModel {

	public ShortTimeModel() {
		super(ParseStringMode.ONE_MAP_MANY);
		this.modelName = "SHORT_TIME_MODEL";
	}

	protected static final String TIME_REGEX_PATTERN = "((\\D)?(\\d{1,2}):(\\d{1,2})?(a?))";
	protected static final Pattern timePattern = Pattern.compile(TIME_REGEX_PATTERN);
 
	@Override
	protected boolean preWordFilter(String word) {
		if (!super.preWordFilter(word)) {
			return false;
		}
		return word.startsWith("_time(");
	}

	@Override
	protected String getStringData(String word) {
		return word.replaceFirst("_time\\((.+)\\)", "$1");
	}

	@Override
	protected Object createData(String word) {
		try {
			Matcher matcher = timePattern.matcher(word);
			if (!matcher.find()) {
				throw new Exception();
			}
			String param = matcher.group(2);
			int hour = Integer.parseInt(matcher.group(3));
			int minute = Integer.parseInt(matcher.group(4));
			if (this.isContextTimeFormat(param)) {
				return Period.hours(hour).plusMinutes(minute);
			}
			return new LocalTime(hour, minute);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	protected Collection<Object> createDatas(String word) {
		try {
			Collection<Object> objects = new ArrayList<>();
			Matcher matcher = timePattern.matcher(word);
			if (!matcher.find()) {
				throw new Exception();
			}
			String param = matcher.group(2);
			int hour = Integer.parseInt(matcher.group(3));
			int minute = Integer.parseInt(matcher.group(4));

			if (this.isContextTimeFormat(param)) {
				Duration duration = Duration.standardHours(hour).plus(Duration.standardMinutes(minute));
				objects.add(duration);//Period.hours(hour).plusMinutes(minute));
			} else {
				objects.add(new LocalTime(hour, minute));
				boolean isAM = matcher.group(5) != null;
				if (isAM) {
					objects.add(DayContext.AM);
				}
			}
			return objects;
		} catch (Exception ex) {
			return null;
		}
	}

	protected boolean isContextTimeFormat(String param) {
		return "+".equals(param);
	}
}
