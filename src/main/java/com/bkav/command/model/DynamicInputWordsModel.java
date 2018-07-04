package com.bkav.command.model;

import java.util.Collection;

import com.bkav.command.common.ModelProcessMode;
import com.bkav.command.struct.ResultsProcess;

/***
 * Model is builded from dynamic collection words input with value map for each word.
 * @param <T> type of value map for each words input.
 */
public abstract class DynamicInputWordsModel<T> extends InputWordsModel<T> {

	public DynamicInputWordsModel(Collection<? extends Object> stream) {
		super(stream);
	}

	@Override
	public ResultsProcess process(ResultsProcess input) {
		boolean isMarkedOrigin = this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED; 
		return this.wordTrieNode.findPharases(input, isMarkedOrigin);
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
