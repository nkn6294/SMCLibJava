package com.bkav.command.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

import com.bkav.struct.ResultsProcess;
import com.bkav.struct.WordTrieNode;

/***
 * Model is builded from dynamic collection words input with value map for each word.
 * @param <T> type of value map for each words input.
 */
public abstract class DynamicInputWordsModel<T> extends InputWordsModel<T> {

	public void test(String[]... commands) {
		Arrays.stream(commands).forEach(command -> 
		this.wordTrieNode.findPharases(command).stream().forEach(System.out::println));
	}
	
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

	public DynamicInputWordsModel(Stream<? extends Object> stream) {
		super(stream);
	}

	@Override
	public ResultsProcess process(ResultsProcess input) {
		return this.wordTrieNode.findPharases(input);
	}

	/***
	 * Dynamic array input words, array[commands] -> array[command[]], created in <i>init</i> method.
	 */
	protected Collection<T> DATA_PROCESSED;
	/***
	 * Create data for node
	 */
	protected abstract T getDataFromStringArray(String[] datas);
	
	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "STATIC_INPUT_WORDS";
	};

	@Override
	protected void createModelTree() {
		this.updateTrieNode(null);
	}
	@Override
	protected void normalInputData(Stream<? extends Object> stream) {
			
	}
	private final WordTrieNode<T> updateTrieNode(Collection<T> collection) {
		this.wordTrieNode = new WordTrieNode<>();
		return this.wordTrieNode;
	}
}
