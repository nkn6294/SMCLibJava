package com.bkav.command.model;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bkav.command.SystemManager;
import com.bkav.command.struct.WordTrieNodeDistinctValues;
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
	
	public ObjectInputWordsModel(Collection<? extends Object> objects) {
		super(objects.stream());
	}
	
	protected List<T> inputs;
	
	@Override
	protected void normalInputData(Stream<? extends Object> stream) {
		this.inputs = stream.map(this::parserObject).filter(Utils::isNotNull).collect(Collectors.toList());
	}

	@Override
	protected void createModelTree() {
		Function<T, String[]> makeOutput = this::getAlias;
		this.wordTrieNode = updateTrieNode(makeOutput, this.inputs.stream(), SystemManager.textProcesser, new WordTrieNodeDistinctValues<>());
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
	 * Return all alias from export from object.
	 * @param object
	 * @return
	 */
	protected abstract String[] getAlias(T object);
}
