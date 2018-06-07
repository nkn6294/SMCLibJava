package com.bkav.command.model.time;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.common.Model;
import com.bkav.struct.ListStringWithMask;
import com.bkav.struct.MaskConfig;
import com.bkav.struct.ResultsProcess;

public class ShortTimeModel implements Model {

	@Override
	public String getModelName() {
		return "SHORT_TIME_MODEL";
	}
	
	@Override
	public ResultsProcess process(ResultsProcess currentResult) {
		String[] words = currentResult.remains();
		if (words.length == 0) {
			return currentResult;
		}
		ListStringWithMask wordsWithMark =  new ListStringWithMask(words);
		wordsWithMark.setConfig(MaskConfig.getDefaultConfig());
		List<Integer> indexs = new ArrayList<>();
		for (int index = 0; index < words.length; index++) {
			String word = words[index];
			if (!word.startsWith("_t_(")) {
				continue;
			}
			LocalTime localTime = this.processTime(word.replaceFirst("_t_\\((.+)\\)", "$1"));
			if (localTime != null) {
				indexs.add(index);
				currentResult.addValue(localTime);
			}
		}
		currentResult.stringsMark().setMarkWithRelativeIndex(indexs);
		return currentResult;
	}
	
	protected LocalTime processTime(String data) {
		try {
			Matcher matcher = timePattern.matcher(data);
			if (!matcher.find()) {
				throw new Exception();
			}
			String param = matcher.group(2);
			int hour = Integer.parseInt(matcher.group(3));
			int minute = Integer.parseInt(matcher.group(4));
			if ("+".equals(param)) {
				LocalTime now = LocalTime.now();
				now = LocalTime.of(now.getHour(), now.getMinute());
				now = now.plusHours(hour);
				now = now.plusMinutes(minute);
				return now;
			}
			return LocalTime.of(hour, minute);
		} catch (Exception ex) {
			return null;
		}
	}
//	protected static final String TIME_REGEX_PATTERN = "_t_\\(((\\D)?(\\d{1,2}):(\\d{1,2})?\\))";
	protected static final String TIME_REGEX_PATTERN = "((\\D)?(\\d{1,2}):(\\d{1,2})?)";
	protected static Pattern timePattern = Pattern.compile(TIME_REGEX_PATTERN);
}
