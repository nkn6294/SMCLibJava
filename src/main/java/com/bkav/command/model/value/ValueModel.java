package com.bkav.command.model.value;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkav.command.common.ModelProcessMode;
import com.bkav.command.data.time.ValueUnit;
import com.bkav.command.model.AbstractModel;
import com.bkav.command.struct.ListStringWithMask;
import com.bkav.command.struct.MaskConfig;
import com.bkav.command.struct.ResultsProcess;

public class ValueModel extends AbstractModel {

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
			if (!word.startsWith("_value(")) {
				continue;
			}
			ValueUnit valueUnit = this.processValueUnit(word.replaceFirst("_value\\((.+)\\)", "$1"));
			if (valueUnit != null) {
				indexs.add(index);
				currentResult.addValue(valueUnit);
			}
		}
		if (this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED) {
			currentResult.stringsMark().setMarkWithRelativeIndex(indexs);			
		}
		return currentResult;
	}
	protected static final String REGEX_PATTERN = "(\\D)?(\\d+)(.+)";
	protected static Pattern pattern = Pattern.compile(REGEX_PATTERN);
	
	@Override
	protected void init() {
		super.init();
		this.modelName = "VALUE_MODEL";
	}
	
	protected ValueUnit processValueUnit(String data) {
		try {
			Matcher matcher = pattern.matcher(data);
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