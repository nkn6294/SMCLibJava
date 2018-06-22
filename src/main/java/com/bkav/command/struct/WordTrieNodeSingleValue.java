package com.bkav.command.struct;

import java.util.ArrayList;
import java.util.Collection;

public class WordTrieNodeSingleValue<T> extends WordTrieNode<T>{

	public WordTrieNodeSingleValue(WordTrieNodeSingleValue<T> parent, T value, String label) {
		super(parent, value, label);
	}

	public WordTrieNodeSingleValue(WordTrieNodeSingleValue<T> parent, T value) {
		super(parent, value);
	}

	public WordTrieNodeSingleValue(WordTrieNodeSingleValue<T> parent) {
		super(parent);
	}
	
	public WordTrieNodeSingleValue() {
		super();
	}

	@Override
	protected void updateValue(T value) {
		if (this.filterValue(value)) {
			this.values.clear();
			this.values.add(value);
		}
	}

	@Override
	protected Collection<T> createValues() {
		return new ArrayList<>(1);
	}

	@Override
	protected WordTrieNode<T> createNewNodeWithLabel(WordTrieNode<T> parent, String label) {
		WordTrieNodeSingleValue<T> nodeParent = null;
		try {
			nodeParent = (WordTrieNodeSingleValue<T>)parent;
		} catch (Exception ex) {
			nodeParent = null;
		}
		return new WordTrieNodeSingleValue<>(nodeParent, null, label);
	}
}
