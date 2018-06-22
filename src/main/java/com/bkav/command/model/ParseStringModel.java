package com.bkav.command.model;

import java.util.Arrays;
import java.util.stream.Stream;

import com.bkav.command.SystemManager;
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
		for (int index = 0; index < remains.length; index++) {
			String word = remains[index];
			T data = this.createData(word);
			if (data != null) {
				input.stringsMark().setMarkWithRelativeIndex(index);
				input.addValue(data);
			}
		}
		return input;
	}


	/***
	 * Create Data from word input (static or dynamic)
	 */
	protected abstract T createData(String word);
}
