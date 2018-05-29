package com.bkav.command.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import com.bkav.struct.ResultsProcess;
import com.bkav.struct.WordTrieNode;

/***
 * Model build over collection words input (static or dynamic), using build word
 * tree model.
 * 
 * Example: array word input {den, dieu hoa, ...} map to {(Device 'den'),
 * (Device 'dieu hoa')}
 * 
 * @param <T>
 *            type of value map for each word.
 */
public abstract class InputWordsModel<T> extends AbstractModel {

	public void test(String[]... commands) {
		Arrays.stream(commands)
				.forEach(command -> this.wordTrieNode.findPharases(command).stream().forEach(System.out::println));
	}

	public InputWordsModel(Stream<? extends Object> stream) {
		super();
		this.normalInputData(stream);
		this.createModelTree();
	}

	@Override
	public ResultsProcess process(ResultsProcess input) {
		return this.wordTrieNode.findPharases(input);
	}

	public WordTrieNode<T> getWordTrieNode() {
		return this.wordTrieNode;
	}

	/***
	 * Normal input data with {@link #normalInputData(Object)} and
	 * reset Tree Model with {@link #createModelTree()}.
	 */
	public void reloadModel(Collection<Object> data) {
		this.normalInputData(data.stream());
		this.createModelTree();
	}

	/***
	 * Tree model for input data.
	 */
	protected WordTrieNode<T> wordTrieNode;

	/***
	 * Normal input to type can process add to tree model.
	 * 
	 * Implement this method with new variable inner sub class.
	 */
	protected abstract void normalInputData(Stream<? extends Object> stream);

	/***
	 * Create Model tree from input (static or dynamic)
	 */
	protected abstract void createModelTree();
}