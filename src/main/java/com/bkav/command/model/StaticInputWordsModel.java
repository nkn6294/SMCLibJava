package com.bkav.command.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

import com.bkav.command.SystemManager;
import com.bkav.command.common.ModelProcessMode;
import com.bkav.command.struct.ResultsProcess;

/***
 * Model is builded from static collection words input with value map for each word.
 * 
 * @param <T> type of value map for each words input.
 */
public abstract class StaticInputWordsModel<T> extends InputWordsModel<T> {
	@Override
	public void test(String[]... commands) {
		Arrays.stream(commands)
				.forEach(command -> this.wordTrieNode.findPharases(command).stream().map(Object::toString).forEach(SystemManager.logger::info));
	}

	public StaticInputWordsModel(Collection<String> dataInput) {
		super(dataInput.stream());
		Arrays.sort(this.dataProcessed, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	public StaticInputWordsModel(String[] dataInput) {
		super(Arrays.stream(dataInput));
		Arrays.sort(this.dataProcessed, DEFAULT_STRING_ARRAY_COMPARATOR);
	}
	
	public StaticInputWordsModel(String[] dataInput, Function<Object, String> convert) {
		super(Arrays.stream(dataInput).map(convert::apply));
		Arrays.sort(this.dataProcessed, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	public StaticInputWordsModel(Collection<Object> dataInput, Function<Object, String> convert) {
		super(dataInput.stream().map(convert::apply));
		Arrays.sort(this.dataProcessed, DEFAULT_STRING_ARRAY_COMPARATOR);
	}
	@Override
	public ResultsProcess process(ResultsProcess input) {
		boolean isMarkedOrigin = this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED;
		return this.wordTrieNode.findPharases(input, isMarkedOrigin);
	}

	/***
	 * data[commands] -> data[command[]], created in {@link #normalInputData(Stream)}
	 */
	protected String[][] dataProcessed;

	@Override
	protected void createModelTree() {
		Function<String[], T> makeValue = this::getDataFromStringArray;
		this.wordTrieNode = updateTrieNode(Arrays.stream(this.dataProcessed), makeValue, createDefaultWordTrieNode());
	}

	@Override
	protected void normalInputData(Stream<? extends Object> stream) {
		this.dataProcessed = stream.map(Object::toString)
				.map(SystemManager.textProcesser::textToWords).toArray(String[][]::new);
	}

	/***
	 * Create data for node from String[] input.
	 */
	protected abstract T getDataFromStringArray(String[] datas);
}
