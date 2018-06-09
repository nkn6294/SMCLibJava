package com.bkav.command.model.time;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.common.Model;
import com.bkav.struct.ListStringWithMask;
import com.bkav.struct.MaskConfig;
import com.bkav.struct.ResultsProcess;

public class ShortDateModel implements Model {

	@Override
	public String getModelName() {
		return "SHORT_DATE_MODEL";
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
			LocalDate localTime = this.processDate(word);
			if (localTime != null) {
				currentResult.stringsMark().setMarkWithRelativeIndex(index);
				currentResult.addValue(localTime);
				break;
			}
		}
		return currentResult;
	}
	
	protected LocalDate processDate(String data) {
		try {
			Matcher matcher = datePattern.matcher(data);
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
	public static final String DATE_REGEX_PATTERN = "((\\d{1,2})-(\\d{1,2})-(\\d{1,2}))";
	protected static Pattern datePattern = Pattern.compile(DATE_REGEX_PATTERN);
}
