package com.bkav.command.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

import com.bkav.SystemManager;
import com.bkav.struct.ResultsProcess;
import com.bkav.struct.WordTrieNode;

/***
 * Model is builded from static collection words input with value map for each word.
 * 
 * @param <T> type of value map for each words input.
 */
public abstract class StaticInputWordsModel<T> extends InputWordsModel<T> {
	public void test(String[]... commands) {
		Arrays.stream(commands)
				.forEach(command -> this.wordTrieNode.findPharases(command).stream().forEach(System.out::println));
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

	public StaticInputWordsModel(Collection<String> dataInput) {
		super(dataInput.stream());
		Arrays.sort(this.DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	public StaticInputWordsModel(String[] dataInput) {
		super(Arrays.stream(dataInput));
		Arrays.sort(this.DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}
	
	public StaticInputWordsModel(String[] dataInput, Function<Object, String> convert) {
		super(Arrays.stream(dataInput).map(convert::apply));
		Arrays.sort(this.DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	public StaticInputWordsModel(Collection<Object> dataInput, Function<Object, String> convert) {
		super(dataInput.stream().map(convert::apply));
		Arrays.sort(this.DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}
	@Override
	public ResultsProcess process(ResultsProcess input) {
		return this.wordTrieNode.findPharases(input);
	}

	/***
	 * data[commands] -> data[command[]], created in {@link #normalInputData(Stream)}
	 */
	protected String[][] DATA_PROCESSED;

	@Override
	protected void createModelTree() {
		Function<String[], T> makeValue = data -> this.getDataFromStringArray(data);
		this.wordTrieNode = updateTrieNode(this.DATA_PROCESSED, makeValue);
	}

	@Override
	protected void normalInputData(Stream<? extends Object> stream) {
		this.DATA_PROCESSED = stream.map(Object::toString)
				.map(SystemManager.textProcesser::textToWords).toArray(String[][]::new);
	}

	/***
	 * Create data for node from String[] input.
	 */
	protected abstract T getDataFromStringArray(String[] datas);

	private final static <T> WordTrieNode<T> updateTrieNode(String[][] datas, Function<String[], T> makeObject) {
		WordTrieNode<T> wordTrieNode = new WordTrieNode<>();
		Arrays.stream(datas).forEach(data -> {
			wordTrieNode.addPhrase(data, makeObject.apply(data));			
		});
		return wordTrieNode;
	}
}
