package com.bkav.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.bkav.command.common.ListStringWithMark;
import com.bkav.command.common.ResultFind;

public class WordTrieNodeManager {

	public WordTrieNodeManager() {
		this.wordTrieNodes = new ArrayList<>();
	}

	@SafeVarargs
	public final void addWordTrieNode(WordTrieNode<?>... nodes) {
		Arrays.stream(nodes).forEach(this.wordTrieNodes::add);
	}

	public static <T> Collection<T> findPharases(String[] words, Collection<T> foundPharases, WordTrieNode<T> rootNode) {
		WordTrieNode<T> currentNode = rootNode;
		if (foundPharases == null) {
			foundPharases = new HashSet<>();
		}
		for (int index = 0; index < words.length;) {
			String word = words[index];
			WordTrieNode<T> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				++index;
			} else {
				if (currentNode.getId() != null) {
					foundPharases.add(currentNode.getId());
				}
				if (currentNode == rootNode) {
					++index;
				} else {
					currentNode = rootNode;
				}
			}
		}
		if (currentNode.getId() != null) {
			foundPharases.add(currentNode.getId());
		}
		return foundPharases;
	}
	
	public static <T> Collection<ResultFind<T>> findPharasesWithRemain(String[] words, Collection<T> foundPharases, WordTrieNode<T> rootNode) {
		WordTrieNode<T> currentNode = rootNode;
		if (foundPharases == null) {
			foundPharases = new HashSet<>();
		}
		ListStringWithMark wordsWithMark = new ListStringWithMark(words);
		Collection<ResultFind<T>> results = new ArrayList<>();
		for (int index = 0; index < words.length;) {
			String word = wordsWithMark.get(index);
			WordTrieNode<T> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				wordsWithMark.setMark(index);
				index++;
			} else {
				if (currentNode.getId() != null) {
					List<String> remains = new ArrayList<>();
					wordsWithMark.forEach(remains::add);
					T value = currentNode.getId();
					ResultFind<T> resultFind = new ResultFind<>(value, remains.toArray(new String[remains.size()]));
					foundPharases.add(value);
					results.add(resultFind);
					wordsWithMark.reset();
				}
				if (currentNode == rootNode) {
					index++;
				} else {
					currentNode = rootNode;
				}
			}
		}
		if (currentNode.getId() != null) {
			T value = currentNode.getId();
			List<String> remains = new ArrayList<>();
			wordsWithMark.forEach(remains::add);
			ResultFind<T> resultFind = new ResultFind<>(value, remains.toArray(new String[remains.size()]));
			foundPharases.add(currentNode.getId());
			results.add(resultFind);
		}
		return results;
	}
	
	public <T> Collection<T> findPharases(Iterator<String> words, Collection<T> foundPharases,  WordTrieNode<T> rootNode) {
		WordTrieNode<T> currentNode = rootNode;
		if (foundPharases == null) {
			foundPharases = new HashSet<>();
		}
		String word = null;
		if (words.hasNext()) {
			word = words.next();
		}
		while (words.hasNext()) {
			WordTrieNode<T> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				word = words.next();
			} else {
				if (currentNode.getId() != null) {
					foundPharases.add(currentNode.getId());
				}
				if (currentNode == rootNode) {
					word = words.next();
				} else {
					currentNode = rootNode;
				}
			}
		}
		if (currentNode.getId() != null) {
			foundPharases.add(currentNode.getId());
		}
		return foundPharases;
	}
	
	protected Collection<WordTrieNode<?>> wordTrieNodes;
}
