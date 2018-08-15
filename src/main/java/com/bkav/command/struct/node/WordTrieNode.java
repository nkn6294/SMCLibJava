package com.bkav.command.struct.node;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bkav.command.common.CommandTextProcesser;
import com.bkav.command.common.TextProcesser;
import com.bkav.command.struct.mask.ListStringWithMask;
import com.bkav.command.struct.mask.MaskConfig;
import com.bkav.command.struct.result.ResultsProcess;

public class WordTrieNode<T> {

	public WordTrieNode(WordTrieNode<T> parent, T value, String label) {
		this.initWithValue(parent, value, label);
	}

	public WordTrieNode(WordTrieNode<T> parent, T value) {
		this.initWithValue(parent, value, null);
	}

	/***
	 * Create WordTrieNode with Collection values using result from
	 * {@link #importValues(Collection)} if <b>null</b> using
	 * {@link #createValues()} after add all element from <b>inputs</b>
	 * 
	 * @param parent
	 * @param values
	 * @param label
	 */
	public WordTrieNode(WordTrieNode<T> parent, Collection<T> inputs, String label) {
		this.initWithCollection(parent, inputs, label);
	}

	public WordTrieNode(WordTrieNode<T> parent) {
		this.initWithCollection(parent, this.createValues(), null);
	}

	public WordTrieNode() {
		this.initWithCollection(parent, this.createValues(), null);
	}

	public Collection<T> values() {
		return this.values;
	}

	public WordTrieNode<T> getParent() {
		return this.parent;
	}

	public boolean isRoot() {
		return this.parent != null;
	}

	public boolean isLeaf() {
		return this.childrens.isEmpty();
	}

	public String getLabel() {
		return this.label;
	}

	public boolean isHasValue() {
		return !this.values.isEmpty();
	}

	public T getValue() {
		if (this.isHasValue()) {
			return this.values.iterator().next();
		}
		return null;
	}

	public String[] getLabels() {
		List<String> listString = new ArrayList<>();
		WordTrieNode<T> node = this;
		while (!node.isRoot()) {
			listString.add(node.getLabel());
			node = node.getParent();
		}
		int size = listString.size();
		String[] output = new String[size];
		for (int index = 0; index < size; index++) {
			output[index] = listString.get(size - 1 - index);
		}
		return output;
	}

	public void addPhrase(String[] words, T value) {
		if (words.length == 0) {
			return;			
		}
		WordTrieNode<T> node = this;
		for (int index = 0; index < words.length; index++) {
			String word = words[index];
			WordTrieNode<T> childNode = node.getChildrens().get(word);
			if (childNode == null) {
				childNode = this.createNewNodeWithLabel(node, word);// new WordTrieNode<>(node, null, word);
				node.getChildrens().put(word, childNode);
			}
			node = childNode;
		}
		node.addValue(value);
	}

	public void addMultiPharase(List<String[]> alias, T value) {
		for (String[] words : alias) {
			this.addPhrase(words, value);
		}
	}

	public void addMultiPharase(String[] alias, T value, CommandTextProcesser textProcesser) {
		for (String alia : alias) {
			String textProcessed = textProcesser.apply(alia);
			String[] words = textProcesser.textToWords(textProcessed);
			this.addPhrase(words, value);
		}
	}

	public void addPhrase(Iterator<String> words, T value) {
		if (!words.hasNext()) {
			return;
		}
		WordTrieNode<T> node = this;
		while (words.hasNext()) {
			String word = words.next();
			WordTrieNode<T> childNode = node.getChildrens().get(word);
			if (childNode == null) {
				childNode = this.createNewNodeWithLabel(node, word);// new WordTrieNode<>(node, null, word);
				node.getChildrens().put(word, childNode);
			}
			node = childNode;
		}
		node.addValue(value);
	}

	public Collection<T> findPharases(String[] words, Collection<T> foundPharases) {
		WordTrieNode<T> node = this;
		if (foundPharases == null) {
			foundPharases = new HashSet<>();
		}
		for (int index = 0; index < words.length;) {
			String word = words[index];
			WordTrieNode<T> childNode = node.getChildrens().get(word);
			if (childNode != null) {
				node = childNode;
				index++;
			} else {
				if (node.isHasValue()) {
					for (T value : node.values) {
						foundPharases.add(value);
					}
				}
				if (node == this) {
					index++;
				} else {
					node = this;
				}
			}
		}
		if (node.isHasValue()) {
			for (T value : node.values) {
				foundPharases.add(value);
			}
		}
		return foundPharases;
	}

	public ResultsProcess findPharases(ResultsProcess currentResult, boolean isMarkedOrigin) {
		return findPharases(currentResult, isMarkedOrigin, null);
	}
	public ResultsProcess findPharases(ResultsProcess currentResult, boolean isMarkedOrigin, TextProcesser textProcesser) {
		WordTrieNode<T> currentNode = this;
		String[] words = currentResult.remains();
		if (words.length == 0) {
			return currentResult;
		}
		ListStringWithMask wordsWithMark = new ListStringWithMask(words);
		wordsWithMark.setConfig(MaskConfig.getDefaultConfig());
		List<Integer> indexs = new ArrayList<>();
		for (int index = 0; index < words.length;) {
			String word = words[index];
			if (textProcesser != null) {
				word = textProcesser.apply(word);
			}
			WordTrieNode<T> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				indexs.add(index);
				index++;
				continue;
			}
			if (currentNode.isHasValue()) {
				for (T value : currentNode.values) {
					currentResult.addValue(value);
				}
				wordsWithMark.setMark(indexs);
			}
			if (currentNode == this) {
				index++;
			} else {
				currentNode = this;
			}
			indexs.clear();
		}
		if (currentNode.isHasValue()) {
			for (T value : currentNode.values) {
				currentResult.addValue(value);
			}
			wordsWithMark.setMark(indexs);
		}
		indexs.clear();
		if (isMarkedOrigin) {
			currentResult.stringsMark().setMarkWithRelativeIndex(wordsWithMark.markIndexs());
		}
		return currentResult;
	}
	public Collection<T> findPharases(String[] words) {
		return findPharases(words, null);
	}

	public Collection<T> findPharases(Iterator<String> words, Collection<T> foundPharases) {
		WordTrieNode<T> node = this;
		if (foundPharases == null) {
			foundPharases = new HashSet<>();
		}
		String word = null;
		if (words.hasNext()) {
			word = words.next();
		}
		while (words.hasNext()) {
			WordTrieNode<T> childNode = node.getChildrens().get(word);
			if (childNode != null) {
				node = childNode;
				word = words.next();
				continue;
			}
			if (node.isHasValue()) {
				for (T value : node.values) {
					foundPharases.add(value);
				}
			}
			if (node == this) {
				word = words.next();
			} else {
				node = this;
			}
		}
		if (node.isHasValue()) {
			for (T value : node.values) {
				foundPharases.add(value);
			}
		}
		return foundPharases;
	}

	public Collection<T> findPharases(Iterator<String> words) {
		return this.findPharases(words, null);
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		writer.append(":[" + this.values + "]");
		if (this.childrens.isEmpty()) {
			return writer.toString();
		}
		writer.append("{");
		Iterator<String> iterator = this.childrens.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			WordTrieNode<T> itemNode = this.childrens.get(key);
			writer.append("{");
			writer.append(key);
			writer.append(itemNode.toString());
			writer.append("}");
			if (iterator.hasNext()) {
				writer.append(";");
			} else {
				break;
			}
		}
		writer.append("}");
		return writer.toString();
	}

	public Map<String, WordTrieNode<T>> getChildrens() {
		return this.childrens;
	}

//	protected void updateValue(T value) {
//		if (this.filterValue(value)) {
//			this.values.add(value);
//		}
//	};

	protected Collection<T> createValues() {
		return new ArrayList<>();
	};

	protected WordTrieNode<T> createNewNodeWithLabel(WordTrieNode<T> parent, String label) {
		return new WordTrieNode<>(parent, new HashSet<T>(), label);
	}

	/***
	 * Filter input value, override to custome.
	 * 
	 * @param value
	 * @return
	 */
	protected boolean filterValue(T value) {
		return value != null;
	}

	/***
	 * Import input values.
	 * 
	 * @param values
	 *            input value.
	 * @return Collections after import.
	 */
	protected Collection<T> importValues(Collection<T> values) {
		return values;
	}

	protected void addValue(T value) {
		if (this.filterValue(value)) {
			this.values.add(value);
		}
	}

	protected String label;
	protected Collection<T> values;
	protected WordTrieNode<T> parent;
	protected final Map<String, WordTrieNode<T>> childrens = new HashMap<>();

	private void initWithValue(WordTrieNode<T> parent, T value, String label) {
		this.label = label;
		this.parent = parent;
		this.values = this.createValues();
		if (this.values == null) {
			throw new RuntimeException("Invalid createValues collection");
		}
		if (value != null) {
			this.values.add(value);
		}
	}

	private void initWithCollection(WordTrieNode<T> parent, Collection<T> inputs, String label) {
		this.label = label;
		this.parent = parent;
		Collection<T> outputs = this.importValues(inputs);
		if (outputs == null) {
			// createValues and add all element.
			outputs = this.createValues();
			if (outputs == null) {
				throw new RuntimeException("Invalid createValues collection");
			}
			this.values = outputs;
			if (inputs != null) {
				for (T input : inputs) {
					if (this.filterValue(input)) {
						this.values.add(input);
					}
				}
			}
		} else {
			this.values = outputs;
		}
	}
}
