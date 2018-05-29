package com.bkav.command.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

import com.bkav.struct.ResultsProcess;
import com.bkav.struct.WordTrieNode;

/***
 * Model build over collection words input (static or dynamic), using build word tree model.
 * 
 * Example: array word input {den, dieu hoa, ...} map to {(Device 'den'),
 * (Device 'dieu hoa')}
 * 
 * @param <T> type of value map for each word.
 */
public abstract class InputWordsModel<T> extends AbstractModel {

	public final static Comparator<String[]> DEFAULT_STRING_ARRAY_COMPARATOR = (array1, array2) -> {
		if (array1.length != array2.length) {
			return array2.length > array2.length ? 1 : -1;
		}
		for (int index = 0; index < array1.length; index++) {
			int compare = array1[index].compareTo(array2[index]);
			if (compare != 0) {
				return compare;				
			}
		}
		return 0;
	};
	
	public final static Comparator<Collection<String>> DEFAULT_COLLECTION_STRING_COMPARATOR = (array1, array2) -> {
		if (array1.size() != array2.size()) {
			return array2.size() > array2.size() ? 1 : -1;
		}
		Iterator<String> iterator1 = array1.iterator();
		Iterator<String> iterator2 = array2.iterator();
		while(iterator1.hasNext() && iterator2.hasNext()) {
			int compare = iterator1.next().compareTo(iterator2.next());
			if (compare != 0) {
				return compare;
			}			
		}
		return 0;
	};
	
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

	protected final static <T> WordTrieNode<T> updateTrieNode(Stream<String[]> stream, Function<String[], T> makeObject) {
		WordTrieNode<T> wordTrieNode = new WordTrieNode<>();
		stream.forEach(data -> {
			wordTrieNode.addPhrase(data, makeObject.apply(data));			
		});
		return wordTrieNode;
	}
	
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
