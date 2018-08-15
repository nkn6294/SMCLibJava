package com.bkav.command.model.common;

import java.util.Collection;

/***
 * Model is builded from dynamic collection words input with value map for each word.
 * @param <T> type of value map for each words input.
 */
public abstract class DynamicInputWordsModel<T> extends InputWordsModel<T> {

	public DynamicInputWordsModel(Collection<? extends Object> stream) {
		super(stream);
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
	protected void normalInputData(Collection<? extends Object> stream) {
			
	}
}
