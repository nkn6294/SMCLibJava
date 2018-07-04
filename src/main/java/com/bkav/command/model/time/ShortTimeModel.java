package com.bkav.command.model.time;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalTime;

import com.bkav.command.common.ModelProcessMode;
import com.bkav.command.model.AbstractModel;
import com.bkav.command.struct.ListStringWithMask;
import com.bkav.command.struct.MaskConfig;
import com.bkav.command.struct.ResultsProcess;

public class ShortTimeModel extends AbstractModel {

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
			if (!word.startsWith("_time(")) {
				continue;
			}
			LocalTime localTime = this.processTime(word.replaceFirst("_time\\((.+)\\)", "$1"));
			if (localTime != null) {
				indexs.add(index);
				currentResult.addValue(localTime);
			}
		}
		if (this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED) {
			currentResult.stringsMark().setMarkWithRelativeIndex(indexs);			
		}
		return currentResult;
	}
	protected static final String TIME_REGEX_PATTERN = "((\\D)?(\\d{1,2}):(\\d{1,2})?)";
	protected static Pattern timePattern = Pattern.compile(TIME_REGEX_PATTERN);

	@Override
	protected void init() {
		super.init();
		this.modelName = "SHORT_TIME_MODEL";
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
				return LocalTime.now().plusHours(hour).plusMinutes(minute);
			}
			return new LocalTime(hour, minute);
		} catch (Exception ex) {
			return null;
		}
	}
}
