package com.bkav.command.model;

import java.util.Collection;
import java.util.stream.Stream;

import com.bkav.command.common.CommandTextProcesser;

/***
 * Model is builded from dynamic collection words input with value map for each word.
 * @param <T> type of value map for each words input.
 */
public abstract class DynamicInputWordsModel<T> extends InputWordsModel<T> {

//	public void test(String[]... commands) {
//		Arrays.stream(commands).forEach(command -> 
//		this.wordTrieNode.findPharases(command).stream()
//			.map(Object::toString)
//			.forEach(SystemManager.logger::info));
//	}

	public DynamicInputWordsModel(Stream<? extends Object> stream) {
		super(stream);
	}
	
	public DynamicInputWordsModel(Stream<? extends Object> stream, CommandTextProcesser textProcesser) {
		super(stream, textProcesser);
	}

	/***
	 * Dynamic array input words, array[commands] -> array[command[]], created in <i>init</i> method.
	 */
	protected Collection<T> dataProcessed;
	/***
	 * Create data for node
	 */
	protected abstract T getDataFromStringArray(String[] datas);
	
	@Override
	protected void init() {
		super.init();
		this.modelName = "DYNAMIC_INPUT_WORDS";
	}

	@Override
	protected void createModelTree() {
		
	}
	@Override
	protected void normalInputData(Stream<? extends Object> stream) {
			
	}
}
