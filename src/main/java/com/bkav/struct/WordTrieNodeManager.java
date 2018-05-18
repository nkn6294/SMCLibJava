package com.bkav.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class WordTrieNodeManager {

	public WordTrieNodeManager() {
		this.wordTrieNodes = new ArrayList<>();
	}

	@SafeVarargs
	public final void addWordTrieNode(WordTrieNode<?>... nodes) {
		Arrays.stream(nodes).forEach(this.wordTrieNodes::add);
	}

	public final ResultNode<?> find(String[] commands) {
		ResultFind<Object> resultFind = new ResultFind<Object>(null, new String[] {}, commands);
		ResultNode<?> rootNode = new ResultNode<>(null, resultFind);
		return processNode(rootNode, this.wordTrieNodes.iterator());
	}
	
	public final ResultTreeNode<?> findAdvance(String[] commands) {
		ResultFind<Object> resultFind = new ResultFind<Object>(null, new String[] {}, commands);
		ResultTreeNode<?> rootNode = new ResultTreeNode<>(null, resultFind);
		return processNodes(rootNode, null, this.wordTrieNodes.iterator());
	}
	
	public final static ResultTreeNode<?> processNodes(ResultTreeNode<?> parent, WordTrieNode<?> wordTrieNode, Iterator<WordTrieNode<?>> iterator) {
		//TODO processNodes not complete
		WordTrieNode<?> currentTrieNode = wordTrieNode;
		if (currentTrieNode == null) {
			if (!iterator.hasNext()) {
				return parent;
			}
			currentTrieNode = iterator.next();
		}
		Collection<ResultTreeNode<?>> childNodes = null;
		while((childNodes = makeChildNodes(parent, currentTrieNode)).size() <= 0) {
			if (!iterator.hasNext()) {
				return parent;
			}
			currentTrieNode = iterator.next();
		}
		// childNodes not empty (1/n/all node with a parent).
		if (!iterator.hasNext()) {
			return parent;
		}
		final WordTrieNode<?> nextTrieNode = iterator.next(); 
		childNodes.forEach(node -> processNodes(node, nextTrieNode, iterator));
		return parent;
	}

	public final static List<ResultTreeNode<?>> makeChildNodes(ResultTreeNode<?> parent, WordTrieNode<?> wordTrieNode) {
		findPharases(parent.getValue(), wordTrieNode).stream()
			.map(result -> new ResultTreeNode<>(parent, result))
			.forEach(parent::addChild);
		return parent.getChilds();
	}
	public static List<ResultFind<?>> findPharases(ResultFind<?> currentResult, WordTrieNode<?> rootNode) {
		WordTrieNode<?> currentNode = rootNode;
		String[] words = currentResult.getRemains();
		ListStringWithMark wordsWithMark = new ListStringWithMark(words);
		List<ResultFind<?>> results = new ArrayList<>();
		for (int index = 0; index < words.length;) {
			String word = wordsWithMark.get(index);
			WordTrieNode<?> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				wordsWithMark.setMark(index);
				index++;
			} else {
				if (currentNode.getId() != null) {
					List<String> remains = new ArrayList<>();
					wordsWithMark.forEach(remains::add);
					Object value = currentNode.getId();
					String[][] marks = wordsWithMark.getMarks();
					ResultFind<?> resultFind = new ResultFind<>(value, 
							marks.length > 0 ? marks[0] : new String[] {},
							remains.toArray(new String[remains.size()]));
					results.add(resultFind);
					wordsWithMark.reset();// ?? save index + not reset.
				}
				if (currentNode == rootNode) {
					index++;
				} else {
					currentNode = rootNode;
				}
			}
		}
		if (currentNode.getId() != null) {
			Object value = currentNode.getId();
			List<String> remains = new ArrayList<>();
			wordsWithMark.forEach(remains::add);
			String[][] marks = wordsWithMark.getMarks();
			ResultFind<?> resultFind = new ResultFind<>(value,
					marks.length > 0 ? marks[0] : new String[] {},
					remains.toArray(new String[remains.size()]));
			results.add(resultFind);
		}
		return results;
	}
	
	public final static ResultNode<?> processNode(ResultNode<?> parent, Iterator<WordTrieNode<?>> iterator) {
		if (!iterator.hasNext() ) {
			return parent;
		}
		ResultNode<?> nextNode = makeNextNode(parent, iterator.next());
		processNode(nextNode, iterator);
		return parent;
	}
	
	public final static ResultNode<?> makeNextNode(ResultNode<?> parent, WordTrieNode<?> wordTrieNode) {
		ResultFind<?> resultFind = findPharase(parent.getValue(), wordTrieNode);
		if (resultFind == parent.getValue()) {
			return parent;
		}
		ResultNode<?> childNode = new ResultNode<>(parent, resultFind);
		parent.setChild(childNode);
		return childNode;
	}
	public static ResultFind<?> findPharase(ResultFind<?> currentResult, WordTrieNode<?> rootNode) {
		List<ResultFind<?>> listResult = findPharases(currentResult, rootNode);
		Collections.sort(listResult, (result1, result2) -> Integer.compare(result1.getRemains().length, result2.getRemains().length));
		if (listResult.size() > 0) {
			return listResult.get(0);
		}
		return currentResult;
	}

	protected Collection<WordTrieNode<?>> wordTrieNodes;
}
