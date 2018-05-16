package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bkav.command.common.Model;
import com.bkav.command.data.CommonData;
import com.bkav.struct.WordTrieNode;

public abstract class CommonModel<T extends CommonData> implements Model {

	
	public final static Comparator<String[]> DEFAULT_STRING_ARRAY_COMPARATOR = (array1, array2) -> {
		// TODO sort with length array decrease
		if (array1.length != array2.length) {
			return array2.length > array2.length ? 1 : -1;
		}
		// TODO sort with equal sum
		for (int index = 0; index < array1.length; index++) {
			int compare = array1[index].compareTo(array2[index]);
			if (compare != 0)
				return compare;
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
			this.wordTrieNode.addPhrase(data, this.createDataFromStringArray(data));
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
	public String[] process(String[] input) {
		return (String[]) this.process(Arrays.stream(input).collect(Collectors.toList())).toArray();
	}

	@Override
	public List<String> process(List<String> input) {
		List<String> output = new ArrayList<>();
		for (int index = 0; index < input.size(); index++) {

		}
		return output;
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
	
	protected abstract T createDataFromStringArray(String[] datas);
	protected void init() {
	};
}
