package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bkav.command.SystemManager;
import com.bkav.command.common.CommandTextProcesser;
import com.bkav.command.common.Function;
import com.bkav.command.struct.WordTrieNodeDistinctValues;

/***
 * 
 * @author namnk Create Model from collections data can exportable to string.
 * @param <T>
 *            type of input.
 */
public abstract class ObjectInputWordsModel<T> extends InputWordsModel<T> {

	public ObjectInputWordsModel(Collection<? extends Object> stream) {
		super(stream);
	}
	public ObjectInputWordsModel(Collection<? extends Object> stream, CommandTextProcesser textProcesser) {
		super(stream, textProcesser);
	}
	protected List<T> inputs;

	@Override

	protected void normalInputData(Collection<? extends Object> stream) {
		this.inputs = new ArrayList<>();
		for (Object object : stream) {
			T value = this.parserObject(object);
			if (value == null) {
				continue;
			}
			this.inputs.add(value);
		}

	}

	@Override
	protected void createModelTree() {
		Function<T, String[]> makeOutput = new Function<T, String[]>() {
			@Override
			public String[] apply(T value) {
				return getAlias(value);
			}
		};
		CommandTextProcesser textProcesser =  this.commandTextProcesser;
		if (textProcesser == null) {
			textProcesser = SystemManager.textProcesser;
		}
		this.wordTrieNode = updateTrieNode(makeOutput, this.inputs, textProcesser,
				new WordTrieNodeDistinctValues<T>());
	}

	@SuppressWarnings("unchecked")
	protected T parserObject(Object input) {
		try {
			return (T) input;
		} catch (Exception ex) {
			return null;
		}
	}

	/***
	 * Return all alias from export from object.
	 * 
	 * @param object
	 * @return
	 */
	protected abstract String[] getAlias(T object);
}
