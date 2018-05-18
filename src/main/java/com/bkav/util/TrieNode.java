package com.bkav.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TrieNode {

    public TrieNode() {
        this.phraseID = -1;
        this.children = new HashMap<>();
    }

    public TrieNode(int id) {
        this.phraseID = id;
        this.children = new HashMap<>();
    }

    public Map<String, TrieNode> getChildren() {
        return this.children;
    }

    public int getId() {
        return this.phraseID;
    }

    public void setId(int id) {
        this.phraseID = id;
    }

    public void addPhrase(String phrase, int phraseId) {
        TrieNode node = this;
        String[] words = phrase.split(" ");
        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            if (!node.getChildren().containsKey(word)) {
                node.getChildren().put(word, new TrieNode());
            }
            node = node.getChildren().get(word);
            if (index == words.length - 1) {
                node.setId(phraseId);
            }
        }
    }

    public List<Integer> findPharases(String textBody) {
        TrieNode node = this;
        List<Integer> foundPharases = new ArrayList<>();
        String[] words = textBody.split(" ");
        for (int index = 0; index < words.length;) {
            String word = words[index];
            if (node.getChildren().containsKey(word)) {
                node = node.getChildren().get(word);
                index++;
            } else {
                if (node.getId() != -1) {
                    foundPharases.add(node.getId());
                }
                if (node == this) {
                    index++;
                } else {
                    node = this;
                }
            }
        }
        if (node.getId() != -1) {
            foundPharases.add(node.getId());
        }
        return foundPharases;
    }

    @Override
    public String toString() {
        if (this.children.isEmpty()) {
            return ":[" + this.phraseID + "]";
        }
        StringWriter writer = new StringWriter();
        writer.append(":[" + this.phraseID + "]");
        writer.append("{");
        Iterator<String> iterator = this.children.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            TrieNode itemNode = this.children.get(key);
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
    private int phraseID;
    private final Map<String, TrieNode> children;
}
