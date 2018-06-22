package com.bkav.command.struct;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bkav.command.util.Utils;

public class WordTrieNodeDistinctValues<T> extends WordTrieNode<T> {

	public WordTrieNodeDistinctValues(WordTrieNodeDistinctValues<T> parent, T value, String label) {
		super(parent, value, label);
	}

	public WordTrieNodeDistinctValues(WordTrieNodeDistinctValues<T> parent, Collection<T> values, String label) {
		super(parent, values, label);
	}

	public WordTrieNodeDistinctValues(WordTrieNodeDistinctValues<T> parent, T value) {
		super(parent, value, null);
	}

	public WordTrieNodeDistinctValues(WordTrieNodeDistinctValues<T> parent) {
		super(parent);
	}

	public WordTrieNodeDistinctValues() {
		super(null);
	}

	@Override
	protected boolean filterValue(T value) {
		boolean done = super.filterValue(value);
		if (done) {
			return Utils.isNotNull(value);
		}
		return done;
	}

	public Collection<T> findPharases(Iterator<String> words) {
		return this.findPharases(words, null);
	}

	@Override
	protected Collection<T> importValues(Collection<T> values) {
		if (values instanceof Set) {
			return values;
		} else {
			return new HashSet<>();
		}
	}

	@Override
	protected void updateValue(T value) {
		if (this.filterValue(value)) {
			this.values.add(value);			
		}
	}

	@Override
	protected Collection<T> createValues() {
		return new HashSet<>();
	}

	@Override
	protected WordTrieNode<T> createNewNodeWithLabel(WordTrieNode<T> parent, String label) {
		WordTrieNodeDistinctValues<T> nodeParent = null;
		try {
			nodeParent = (WordTrieNodeDistinctValues<T>)parent;
		} catch (Exception ex) {
			nodeParent = null;
		}
		return new WordTrieNodeDistinctValues<>(nodeParent, new HashSet<>(), label);
	}
}
