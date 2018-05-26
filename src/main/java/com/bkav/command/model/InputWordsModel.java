package com.bkav.command.model;

import java.util.Arrays;

import com.bkav.struct.ResultsProcess;
import com.bkav.struct.WordTrieNode;

/***
 * Model build over collection words input (static or dynamic), using build word tree model.
 * 
 * Example: array word input {den, dieu hoa, ...} map to {(Device 'den'),  (Device 'dieu hoa')}
 * @param <T> type of value map for each word.
 */
public abstract class InputWordsModel<T> extends AbstractModel {

	public void test(String[]... commands) {
		Arrays.stream(commands).forEach(command -> 
		this.wordTrieNode.findPharases(command).stream().forEach(System.out::println));
	}

	public InputWordsModel() {
		super();
		this.createModelTree();
	}

	@Override
	public ResultsProcess process(ResultsProcess input) {
		return this.wordTrieNode.findPharases(input);
	}

	public WordTrieNode<T> getWordTrieNode() {
		return this.wordTrieNode;
	}
	
	public void reloadModel(Object data) {
		this.createModelTree();
	}
	
	/***
	 * Tree model for input data.
	 */
	protected WordTrieNode<T> wordTrieNode;
	
	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "INPUT_WORDS";
	};
	
	protected abstract void normalInputData(Object data);
	/***
	 * Create Model tree from input (static or dynamic)
	 */
	protected abstract void createModelTree();
}
