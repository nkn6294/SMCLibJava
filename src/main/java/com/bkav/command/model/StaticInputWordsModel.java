package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.bkav.command.SMCManager;
import com.bkav.command.common.CommandTextProcesser;
import com.bkav.command.common.Function;
import com.bkav.command.struct.WordTrieNode;

/***
 * Model is builded from static collection words input with value map for each word.
 * 
 * @param <T> type of value map for each words input.
 */
public abstract class StaticInputWordsModel<T> extends InputWordsModel<T> {
	
	public static Collection<String> stringsToCollection(String[] dataInput) {
		List<String> data = new ArrayList<>();
		for (int index = 0; index < dataInput.length; index++) {
			data.add(dataInput[index]);
		}
		return data;
	}
	
	public StaticInputWordsModel(Collection<String> dataInput) {
		super(dataInput);
		Collections.sort(this.dataProcessed, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	public StaticInputWordsModel(Collection<String> dataInput, CommandTextProcesser textProcesser) {
		super(dataInput, textProcesser);
		Collections.sort(this.dataProcessed, DEFAULT_STRING_ARRAY_COMPARATOR);
	}
	
	public StaticInputWordsModel(String[] dataInput) {
		this(stringsToCollection(dataInput));
	}
	public StaticInputWordsModel(String[] dataInput, CommandTextProcesser textProcesser) {
		this(stringsToCollection(dataInput), textProcesser);
	}
	/***
	 * data[commands] -> data[command[]], created in {@link #normalInputData(Stream)}
	 */
	protected List<String[]> dataProcessed;

	@Override
	protected void createModelTree() {
		Function<String[], T> makeValue = new Function<String[], T>() {
			@Override
			public T apply(String[] value) {
				return getDataFromStringArray(value);
			}
		};
		WordTrieNode<T> defaultNode = createDefaultWordTrieNode();
		this.wordTrieNode = updateTrieNode(this.dataProcessed, makeValue, defaultNode);
	}

	@Override
	protected void normalInputData(Collection<? extends Object> stream) {
		List<String[]> listOutput = new ArrayList<>();
		for (Object object: stream) {
			String objectString = SMCManager.textProcesser.apply(object.toString());
			String data[] = SMCManager.textProcesser.textToWords(objectString);
			listOutput.add(data);
		}
		this.dataProcessed = listOutput;

	}

	/***
	 * Create data for node from String[] input.
	 */
	protected abstract T getDataFromStringArray(String[] datas);
}
