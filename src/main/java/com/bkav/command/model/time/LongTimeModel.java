package com.bkav.command.model.time;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.common.Model;
import com.bkav.struct.ListStringWithMask;
import com.bkav.struct.MaskConfig;
import com.bkav.struct.ResultsProcess;

public class LongTimeModel implements Model {

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
		for (int index = 0; index < words.length; index++) {
			String word = words[index];
			LocalTime localTime = this.processTime(word);
			if (localTime != null) {
				currentResult.stringsMark().setMarkWithRelativeIndex(index);
				currentResult.addValue(localTime);
				break;
			}
		}
		return currentResult;
	}
	
	protected LocalTime processTime(String data) {
		try {
			Matcher matcher = timePattern.matcher(data);
			if (!matcher.find()) {
				throw new Exception();
			}
			int hour = Integer.parseInt(matcher.group(1));
			int minute = Integer.parseInt(matcher.group(2));
			return LocalTime.of(hour, minute);
		} catch (Exception ex) {
			return null;
		}
	}
	public static final String TIME_REGEX_PATTERN = "(\\d+ giờ)(.*) (\\d+ phút)|(\\d+ giờ)|(\\d+ phút)";
	protected static Pattern timePattern = Pattern.compile(TIME_REGEX_PATTERN);
}
