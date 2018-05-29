package com.bkav.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.bkav.command.SystemManager;

public class WordTrieNodeManager {

	public WordTrieNodeManager() {
		this.wordTrieNodes = new ArrayList<>();
	}

	public static final ResultTreeNode<?> processNodes(ResultTreeNode<?> parent, WordTrieNode<?> wordTrieNode,
			Iterator<WordTrieNode<?>> iterator) {
		// TODO processNodes not complete
		WordTrieNode<?> currentTrieNode = wordTrieNode;
		if (currentTrieNode == null) {
			if (!iterator.hasNext()) {
				return parent;
			}
			currentTrieNode = iterator.next();
		}
		Collection<ResultTreeNode<?>> childNodes = null;
		while ((childNodes = makeChildNodes(parent, currentTrieNode)).isEmpty()) {
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

	public static final List<ResultTreeNode<?>> makeChildNodes(ResultTreeNode<?> parent, WordTrieNode<?> wordTrieNode) {
		findPharasesWithReset(parent.getValue(), wordTrieNode).stream()
				.map(result -> new ResultTreeNode<>(parent, result)).forEach(parent::addChild);
		return parent.getChilds();
	}
	public static List<ResultFind<?>> findPharasesWithReset(ResultFind<?> currentResult, WordTrieNode<?> rootNode) {
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
				Object valueNode = currentNode.getId();
				if (valueNode != null) {
					String[][] marks = wordsWithMark.getFragments();
					if (marks.length > 0) {
						ResultFind<?> resultFind = new ResultFind<>(valueNode, marks[0],
								wordsWithMark.unMarkStream().toArray(String[]::new));
						results.add(resultFind);
						wordsWithMark.reset();// ?? save index + not reset.
					}
				}
				if (currentNode == rootNode) {
					index++;
				} else {
					currentNode = rootNode;
				}
			}
		}
		if (currentNode.getId() != null) {
			String[][] marks = wordsWithMark.getFragments();
			if (marks.length > 0) {
				ResultFind<?> resultFind = new ResultFind<>(currentNode.getId(), marks[0],
						wordsWithMark.unMarkStream().toArray(String[]::new));
				results.add(resultFind);
				wordsWithMark.reset();
			}
		}
		return results;
	}

	public static <T> ResultsFind<T> findPharases(ResultsFind<?> currentResult, WordTrieNode<T> rootNode) {
		WordTrieNode<T> currentNode = rootNode;
		String[] words = currentResult.remains();
		ListStringWithMark wordsWithMark = new ListStringWithMark(words);
		List<T> results = new ArrayList<>();
		for (int index = 0; index < words.length;) {
			String word = wordsWithMark.get(index);
			WordTrieNode<T> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				wordsWithMark.setMark(index);
				index++;
			} else {
				if (currentNode.getId() != null) {
					results.add(currentNode.getId());
				}
				wordsWithMark.resetFragment(index);
				if (currentNode == rootNode) {
					index++;
				} else {
					currentNode = rootNode;
				}
			}
		}
		if (currentNode.getId() != null) {
			results.add(currentNode.getId());
		} else {
			try {
				wordsWithMark.resetFragment(words.length - 1);				
			} catch(Exception ex) {
				SystemManager.logger.info(ex.getMessage());
			}
		}
		String[] remains = wordsWithMark.unMarkStream().toArray(String[]::new);
		return new ResultsFind<>(remains, results);//? results.length = 0
	}

	public static final ResultNode<?> processNode(ResultNode<?> parent, Iterator<WordTrieNode<?>> iterator) {
		if (!iterator.hasNext()) {
			return parent;
		}
		ResultNode<?> nextNode = makeNextNode(parent, iterator.next());
		processNode(nextNode, iterator);
		return parent;
	}

	public static final ResultNode<?> makeNextNode(ResultNode<?> parent, WordTrieNode<?> wordTrieNode) {
		ResultFind<?> resultFind = findPharase(parent.getValue(), wordTrieNode);
		if (resultFind == parent.getValue()) {
			return parent;
		}
		ResultNode<?> childNode = new ResultNode<>(parent, resultFind);
		parent.setChild(childNode);
		return childNode;
	}

	public static ResultFind<?> findPharase(ResultFind<?> currentResult, WordTrieNode<?> rootNode) {
		List<ResultFind<?>> listResult = findPharasesWithReset(currentResult, rootNode);
		Collections.sort(listResult,
				(result1, result2) -> Integer.compare(result1.getRemains().length, result2.getRemains().length));
		if (!listResult.isEmpty()) {
			return listResult.get(0);
		}
		return currentResult;
	}

	@SafeVarargs
	public final void addWordTrieNode(WordTrieNode<?>... nodes) {
		Arrays.stream(nodes).forEach(this.wordTrieNodes::add);
	}

	public final ResultNode<?> find(String[] commands) {
		ResultFind<Object> resultFind = new ResultFind<>(null, new String[] {}, commands);
		ResultNode<?> rootNode = new ResultNode<>(null, resultFind);
		return processNode(rootNode, this.wordTrieNodes.iterator());
	}

	public final ResultTreeNode<?> findAdvance(String[] commands) {
		ResultFind<Object> resultFind = new ResultFind<>(null, new String[] {}, commands);
		ResultTreeNode<?> rootNode = new ResultTreeNode<>(null, resultFind);
		return processNodes(rootNode, null, this.wordTrieNodes.iterator());
	}

	protected Collection<WordTrieNode<?>> wordTrieNodes;
}
