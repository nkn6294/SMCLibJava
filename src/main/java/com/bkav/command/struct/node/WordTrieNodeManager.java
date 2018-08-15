package com.bkav.command.struct.node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.bkav.command.SMCManager;
import com.bkav.command.struct.mask.ListStringWithMask;
import com.bkav.command.struct.result.ResultFind;
import com.bkav.command.struct.result.ResultNode;
import com.bkav.command.struct.result.ResultTreeNode;
import com.bkav.command.struct.result.ResultsFind;

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
		for (ResultTreeNode<?> node : childNodes) {
			processNodes(node, nextTrieNode, iterator);
		}
		return parent;
	}

	public static final List<ResultTreeNode<?>> makeChildNodes(ResultTreeNode<?> parent, WordTrieNode<?> wordTrieNode) {
		for (ResultFind<?> result : findPharasesWithReset(parent.getValue(), wordTrieNode)) {
			ResultTreeNode<?> resultNode = new ResultTreeNode<>(parent, result);
			parent.addChild(resultNode);
		}
		return parent.getChilds();
	}

	public static List<ResultFind<?>> findPharasesWithReset(ResultFind<?> currentResult, WordTrieNode<?> rootNode) {
		WordTrieNode<?> currentNode = rootNode;
		String[] words = currentResult.getRemains();
		ListStringWithMask wordsWithMark = new ListStringWithMask(words);
		List<ResultFind<?>> results = new ArrayList<>();
		for (int index = 0; index < words.length;) {
			String word = wordsWithMark.get(index);
			WordTrieNode<?> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				wordsWithMark.setMark(index);
				index++;
			} else {
				Object valueNode = currentNode.getValue();
				if (valueNode != null) {
					String[][] marks = wordsWithMark.getStringFragments();
					if (marks.length > 0) {
						ResultFind<?> resultFind = new ResultFind<>(valueNode, marks[0],
								wordsWithMark.unMarkString());
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
		if (currentNode.getValue() != null) {
			String[][] marks = wordsWithMark.getStringFragments();
			if (marks.length > 0) {
				ResultFind<?> resultFind = new ResultFind<>(currentNode.getValue(), marks[0],
						wordsWithMark.unMarkString());
				results.add(resultFind);
				wordsWithMark.reset();
			}
		}
		return results;
	}

	public static <T> ResultsFind<T> findPharases(ResultsFind<?> currentResult, WordTrieNode<T> rootNode) {
		WordTrieNode<T> currentNode = rootNode;
		String[] words = currentResult.remains();
		ListStringWithMask wordsWithMark = new ListStringWithMask(words);
		List<T> results = new ArrayList<>();
		for (int index = 0; index < words.length;) {
			String word = wordsWithMark.get(index);
			WordTrieNode<T> childNode = currentNode.getChildrens().get(word);
			if (childNode != null) {
				currentNode = childNode;
				wordsWithMark.setMark(index);
				index++;
			} else {
				if (currentNode.getValue() != null) {
					results.add(currentNode.getValue());
				}
				wordsWithMark.resetFragment(index);
				if (currentNode == rootNode) {
					index++;
				} else {
					currentNode = rootNode;
				}
			}
		}
		if (currentNode.getValue() != null) {
			results.add(currentNode.getValue());
		} else {
			try {
				wordsWithMark.resetFragment(words.length - 1);
			} catch (Exception ex) {
				SMCManager.logger.info(ex.getMessage());
			}
		}
		String[] remains = wordsWithMark.unMarkString();
		return new ResultsFind<>(remains, results);// ? results.length = 0
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
		Collections.sort(listResult, new Comparator<ResultFind<?>>() {
			public int compare(ResultFind<?> result1, ResultFind<?> result2) {
				return Integer.compare(result1.getRemains().length, result2.getRemains().length);
			}
		});
		if (!listResult.isEmpty()) {
			return listResult.get(0);
		}
		return currentResult;
	}

	@SafeVarargs
	public final void addWordTrieNode(WordTrieNode<?>... nodes) {
		for (WordTrieNode<?> node : nodes) {
			this.wordTrieNodes.add(node);
		}
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
