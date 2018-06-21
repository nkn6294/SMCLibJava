package com.bkav.command.model;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bkav.command.util.Utils;

/***
 * 
 * @author namnk
 * Create Model from collections data can exportable to string.
 * @param <T> type of input.
 */
public abstract class ObjectInputWordsModel<T> extends InputWordsModel<T> {

	public ObjectInputWordsModel(Stream<? extends Object> stream) {
		super(stream);
	}

	protected List<T> inputs;
	
	@Override
	protected void normalInputData(Stream<? extends Object> stream) {
		this.inputs = stream.map(this::parserObject).filter(Utils::isNotNull).collect(Collectors.toList());
	}

	@Override
	protected void createModelTree() {
		Function<T, String[]> makeOutput = this::outputData;
		this.wordTrieNode = updateTrieNode(makeOutput, this.inputs.stream());
	}
	

	@SuppressWarnings("unchecked")
	protected T parserObject(Object input) {
		try {
			return (T)input;
		} catch (Exception ex) {
			return null;
		}
	}
	/***
	 * Export String[] from object.
	 * @param object
	 * @return
	 */
	protected abstract String[] outputData(T object);
}
