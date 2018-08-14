package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import com.bkav.command.SMCManager;
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

	public enum ParseStringMode {
		ONE_MAP_ONE,
		ONE_MAP_MANY
	}
	
	public ParseStringModel(ParseStringMode mode) {
		this.parseStringMode = mode;
	}
	public ParseStringModel() {
		this.parseStringMode = ParseStringMode.ONE_MAP_ONE;
	}
	
	public void test(String[]... commands) {
		Arrays.stream(commands).map(words -> new ResultsProcess(words))
			.map(this::process)
			.map(Object::toString)
			.forEach(SMCManager.logger::info);
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
			if (this.parseStringMode == ParseStringMode.ONE_MAP_ONE) {
				T data = this.createData(stringData);
				if (data != null) {
					indexs.add(index);
					input.addValue(data);
				}
			} else if (this.parseStringMode == ParseStringMode.ONE_MAP_MANY) {
				Collection<T> datas = this.createDatas(stringData);
				if (datas != null) {
					indexs.add(index);
					input.addValues(datas);
				}				
			}
		}
		if (this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED) {
			input.stringsMark().setMarkWithRelativeIndex(indexs);			
		}
		return input;
	}

	protected ParseStringMode parseStringMode;
	
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
	 * Create Data from word input (static or dynamic) with mode {@link ParseStringMode#ONE_MAP_ONE}
	 */
	protected abstract T createData(String word);
	/***
	 * Create Data from word input (static or dynamic) with mode {@link ParseStringMode#ONE_MAP_MANY}
	 * Default return {@link Collection} with one item using {@link ParseStringModel#createData(String)}
	 */
	protected Collection<T> createDatas(String word) {
		Collection<T> output = new ArrayList<>();
		output.add(this.createData(word));
		return output;
	}
}
