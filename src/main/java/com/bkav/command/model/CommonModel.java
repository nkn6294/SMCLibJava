package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import com.bkav.command.common.Model;
import com.bkav.command.data.CommonData;
import com.bkav.struct.ResultsProcess;
import com.bkav.struct.WordTrieNode;

public abstract class CommonModel<T extends CommonData> implements Model {

	
	public final static Comparator<String[]> DEFAULT_STRING_ARRAY_COMPARATOR = (array1, array2) -> {
		// sort with length array decrease
		if (array1.length != array2.length) {
			return array2.length > array2.length ? 1 : -1;
		}
		// sort with equal sum
		for (int index = 0; index < array1.length; index++) {
			int compare = array1[index].compareTo(array2[index]);
			if (compare != 0) {
				return compare;				
			}
		}
		return 0;
	};

	public CommonModel() {
		this.init();
		this.updateTrieNode(null);
	}

	public final WordTrieNode<T> updateTrieNode(String[][] datas) {
		this.wordTrieNode = new WordTrieNode<>();
		if (datas == null) {
			datas = DATA_PROCESSED;
		}
		if (datas == null) {
			return this.wordTrieNode;
		}
		for (String[] data : datas) {
			this.wordTrieNode.addPhrase(data, this.getDataFromStringArray(data));
		}
		return this.wordTrieNode;
	}

	public String getModelName() {
		return MODEL_NAME;
	} 
	
	@Override
	public Collection<CommonData> getModelValue() {
		return this.values;
	}

	@Override
	public ResultsProcess process(ResultsProcess input) {
		if (this.wordTrieNode != null) {
			return this.wordTrieNode.findPharases(input);
		}
		return input;
	}

	public void test(String[]... commands) {
		Arrays.stream(commands).forEach(command -> this.wordTrieNode.findPharases(command).stream().forEach(System.out::println));
	}

	public WordTrieNode<T> getWordTrieNode() {
		return this.wordTrieNode;
	}
	
	protected String MODEL_NAME = "COMMON";
	protected String[][] DATA_PROCESSED;
	protected Collection<CommonData> values = new ArrayList<>();
	protected WordTrieNode<T> wordTrieNode;
	/***
	 * Create data for node
	 */
	protected abstract T getDataFromStringArray(String[] datas);
	protected void init() {
	};
}
