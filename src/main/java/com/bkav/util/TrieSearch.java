package com.bkav.util;

import java.util.ArrayList;
import java.util.List;

public class TrieSearch {

	public void addPhrase(TrieNode root, String[] words, int phraseID) {
		TrieNode node = root;
		for (int index = 0; index < words.length; index++) {
			if (node.getChildren().containsKey(words[index]) == false) {
				node.getChildren().put(words[index], new TrieNode());
			}
			node = node.getChildren().get(words[index]);
			if (index == words.length - 1) {
				node.setId(phraseID);
			}
		}
	}

	public List<Integer> findPharases(TrieNode root, String[] words) {
		TrieNode node = root;
		List<Integer> foundPhrases = new ArrayList<>();
		for (int index = 0; index < words.length;) {
			if (node.getChildren().containsKey(words[index])) {
				node = node.getChildren().get(words[index]);
				index++;
			} else {
				if (node.getId() != -1) {
					foundPhrases.add(node.getId());
				}
				if (node == root) {
					index++;
				} else {
					node = root;
				}
			}
		}
		if (node.getId() != -1) {
			foundPhrases.add(node.getId());
		}
		return foundPhrases;
	}

}
