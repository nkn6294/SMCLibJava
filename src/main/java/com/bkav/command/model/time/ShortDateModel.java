package com.bkav.command.model.time;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;

import com.bkav.command.common.ModelProcessMode;
import com.bkav.command.model.AbstractModel;
import com.bkav.command.struct.ListStringWithMask;
import com.bkav.command.struct.MaskConfig;
import com.bkav.command.struct.ResultsProcess;

public class ShortDateModel extends AbstractModel {

	@Override
	public ResultsProcess process(ResultsProcess currentResult) {
		String[] words = currentResult.remains();
		if (words.length == 0) {
			return currentResult;
		}
		ListStringWithMask wordsWithMark = new ListStringWithMask(words);
		wordsWithMark.setConfig(MaskConfig.getDefaultConfig());
		List<Integer> indexs = new ArrayList<>();
		for (int index = 0; index < words.length; index++) {
			String word = words[index];
			if (!word.startsWith("_date(")) {
				continue;
			}
			LocalDate localTime = this.processDate(word.replaceFirst("_date\\((.+)\\)", "$1"));
			if (localTime != null) {
				indexs.add(index);
				currentResult.addValue(localTime);
				break;
			}
		}
		if (this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED) {
			currentResult.stringsMark().setMarkWithRelativeIndex(indexs);
		}
		return currentResult;
	}

	public static final String DATE_REGEX_PATTERN = "((\\d{1,2})-(\\d{1,2})-(\\d{1,2}))";
	protected static Pattern datePattern = Pattern.compile(DATE_REGEX_PATTERN);

	@Override
	protected void init() {
		super.init();
		this.modelName = "SHORT_DATE_MODEL";
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
			return new LocalDate(year, month, dayOfMonth);
		} catch (Exception ex) {
			return null;
		}
	}
}
