package com.bkav.command.model.common;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import com.bkav.command.SMCManager;
import com.bkav.command.common.CommandTextProcesser;
import com.bkav.command.common.Function;
import com.bkav.command.common.ModelProcessMode;
import com.bkav.command.struct.node.WordTrieNode;
import com.bkav.command.struct.node.WordTrieNodeDistinctValues;
import com.bkav.command.struct.result.ResultsProcess;

/***
 * Model build over collection words input (static or dynamic), using build word
 * tree model.
 * 
 * Example: array word input {den, dieu hoa, ...} map to {(Device 'den'),
 * (Device 'dieu hoa')}
 * 
 * @param <T>  type of value map for each word.
 */
public abstract class InputWordsModel<T> extends AbstractModel {

	public static final Comparator<String[]> DEFAULT_STRING_ARRAY_COMPARATOR = new Comparator<String[]>() {

		@Override
		public int compare(String[] array1, String[] array2) {
			if (array1.length != array2.length) {
				return array1.length > array2.length ? 1 : -1;
			}
			for (int index = 0; index < array1.length; index++) {
				int compare = array1[index].compareTo(array2[index]);
				if (compare != 0) {
					return compare;
				}
			}
			return 0;
		}
	};

	public static final Comparator<Collection<String>> DEFAULT_COLLECTION_STRING_COMPARATOR = new Comparator<Collection<String>>() {

		@Override
		public int compare(Collection<String> array1, Collection<String> array2) {
			if (array1.size() != array2.size()) {
				return array1.size() > array2.size() ? 1 : -1;
			}
			Iterator<String> iterator1 = array1.iterator();
			Iterator<String> iterator2 = array2.iterator();
			while (iterator1.hasNext() && iterator2.hasNext()) {
				int compare = iterator1.next().compareTo(iterator2.next());
				if (compare != 0) {
					return compare;
				}
			}
			return 0;
		}
	};

	public InputWordsModel(Collection<? extends Object> data, CommandTextProcesser textProcesser) {
		super();
		this.commandTextProcesser = textProcesser;
		this.normalInputData(data);
		this.createModelTree();
	}
	
	public InputWordsModel(Collection<? extends Object> data) {
		this(data, null);
	}

	@Override
	public ResultsProcess process(ResultsProcess input) {
		boolean isMarkedOrigin = this.modelConfig.getModelProcessMode() == ModelProcessMode.PROCESS_AND_MARKED;
		return this.wordTrieNode.findPharases(input, isMarkedOrigin, this.commandTextProcesser);
	}

	public WordTrieNode<T> getWordTrieNode() {
		return this.wordTrieNode;
	}

	/***
	 * Normal input data with {@link #normalInputData(Object)} and reset Tree Model
	 * with {@link #createModelTree()}.
	 */

	public void reloadModel(Collection<? extends Object> data) {
		this.normalInputData(data);
		this.createModelTree();
	}
	
	public CommandTextProcesser commandTextProcesser() {
		return commandTextProcesser;
	}

	public void commandTextProcesser(CommandTextProcesser commandTextProcesser) {
		this.commandTextProcesser = commandTextProcesser;
	}

	/***
	 * Tree model for input data.
	 */
	protected WordTrieNode<T> wordTrieNode;
	protected CommandTextProcesser commandTextProcesser;

	protected static final <T> WordTrieNode<T> createDefaultWordTrieNode() {
		return new WordTrieNodeDistinctValues<T>();
	}

	protected static final CommandTextProcesser getDefaultCommandTextProcesser() {
		return SMCManager.textProcesser;
	}

	protected static final <T> WordTrieNode<T> updateTrieNode(Collection<String[]> datas,
			Function<String[], T> makeObject, WordTrieNode<T> wordTrieNode) {
		final WordTrieNode<T> rootNode;
		if (wordTrieNode == null) {
			rootNode = createDefaultWordTrieNode();
		} else {
			rootNode = wordTrieNode;
		}
		for (String[] data : datas) {
			rootNode.addPhrase(data, makeObject.apply(data));
		}
		return rootNode;
	}

	protected static final <T> WordTrieNode<T> updateTrieNode(Collection<String[]> stream,
			Function<String[], T> makeObject) {
		return updateTrieNode(stream, makeObject, null);
	}

	protected static final <T> WordTrieNode<T> updateTrieNode(Function<T, String[]> getAlias, Collection<T> stream,
			WordTrieNode<T> wordTrieNode) {
		final WordTrieNode<T> rootNode;
		if (wordTrieNode == null) {
			rootNode = createDefaultWordTrieNode();
		} else {
			rootNode = wordTrieNode;
		}
		for (T data : stream) {
			rootNode.addPhrase(getAlias.apply(data), data);
		}
		return rootNode;
	}

	protected static final <T> WordTrieNode<T> updateTrieNode(Function<T, String[]> getAlias, Collection<T> datas,
			CommandTextProcesser textProcesser, WordTrieNode<T> wordTrieNode) {
		final WordTrieNode<T> rootNode;
		final CommandTextProcesser commandTextProcesser;
		if (wordTrieNode == null) {
			rootNode = createDefaultWordTrieNode();
		} else {
			rootNode = wordTrieNode;
		}
		if (textProcesser == null) {
			commandTextProcesser = getDefaultCommandTextProcesser();
		} else {
			commandTextProcesser = textProcesser;
		}
		for (T data : datas) {
			rootNode.addMultiPharase(getAlias.apply(data), data, commandTextProcesser);
		}
		return rootNode;
	}

	protected static final <T> WordTrieNode<T> updateTrieNode(Function<T, String[]> getAlias, Collection<T> datas,
			CommandTextProcesser textProcesser) {
		return updateTrieNode(getAlias, datas, textProcesser, null);
	}

	protected static final <T> WordTrieNode<T> updateTrieNode(Function<T, String[]> getAlias, Collection<T> datas) {
		return updateTrieNode(getAlias, datas, null, null);
	}

	/***
	 * Normal input to type can process add to tree model.
	 * 
	 * Implement this method with new variable inner sub class.
	 */
	protected abstract void normalInputData(Collection<? extends Object> data);

	/***
	 * Create Model tree from input (static or dynamic)
	 */
	protected abstract void createModelTree();
}
