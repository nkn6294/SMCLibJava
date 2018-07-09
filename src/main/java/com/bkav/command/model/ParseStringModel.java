package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.bkav.command.SystemManager;
import com.bkav.command.common.ModelProcessMode;
import com.bkav.command.struct.ResultsProcess;

/***
 * Model build over by parse word|sign.
 * 
 * Example: array word input contain special sign, 
 * Ex: after preprocser normal input, 'Thu sau' ->_e6 -> DayOfWeek Friday.
 * Note: parse word without space char.
 */
public abstract class ParseStringModel<T> extends AbstractModel {

	public ParseStringModel() {
	}
	
	@Override
	public void test(String[]... commands) {
		Arrays.stream(commands).map(words -> new ResultsProcess(words))
			.map(this::process)
			.map(Object::toString)
			.forEach(SystemManager.logger::info);
	}

	public ParseStringModel(Stream<? extends Object> stream) {
		super();
	}

	@Override
	public ResultsProcess process(ResultsProcess input) {
		String[] remains = input.remains();
		List<Integer> indexs = new ArrayList<>();
		for (int index = 0; index < remains.length; index++) {
			String word = remains[index];
			if (!this.preWordFilter(word)) {
				continue;
			}
			String stringData = this.getStringData(word);
			T data = this.createData(stringData);
			if (data != null) {
				indexs.add(index);
				input.addValue(data);
			}
		}
		if (this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED) {
			input.stringsMark().setMarkWithRelativeIndex(indexs);			
		}
		return input;
	}

	protected boolean preWordFilter(String word) {
		if (word == null) {
			return false;
		}
		return !word.isEmpty();
	}
	protected String getStringData(String word) {
		return word;
	}
	/***
	 * Create Data from word input (static or dynamic)
	 */
	protected abstract T createData(String word);
}
