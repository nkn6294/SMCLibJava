package com.bkav.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import com.bkav.util.StreamUtils;

public class ListStringWithMark implements Iterable<String> {
	public ListStringWithMark(String[] strings) {
		this.init(strings);
	}

	public int length() {
		return this.strings.length;
	}

	public String get(int index) {
		this.checkValidIndex(index);
		return this.strings[index];
	}

	public boolean isMark(int index) {
		this.checkValidIndex(index);
		return this.marks[index] > 0;
	}

	public void setMark(int... indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(index -> this.marks[index]++);
	}

	public String[][] getMarks() {
		List<String[]> result = new ArrayList<>();
		List<String> item = new ArrayList<>();
		for (int index = 0; index < this.strings.length; index++) {
			if (isMark(index)) {
				item.add(strings[index]);
			} else if (item.size() > 0) {
				result.add(item.toArray(new String[item.size()]));
				item.clear();
			}
		}
		return result.toArray(new String[result.size()][]);
	}
	
	public String[][] getUnMarks() {
		List<String[]> result = new ArrayList<>();
		List<String> item = new ArrayList<>();
		for (int index = 0; index < this.strings.length; index++) {
			if (!isMark(index)) {
				item.add(strings[index]);
			} else if (item.size() > 0) {
				result.add(item.toArray(new String[item.size()]));
				item.clear();
			}
		}
		return result.toArray(new String[result.size()][]);
	}

	public void reset(int... indexs) {
		if (indexs.length == 0) {
			this.init(this.strings);
		} else {
			Arrays.stream(indexs).filter(this::isValidIndex).forEach(index -> this.marks[index] = 0);
		}
	}

	public String[] getStrings() {
		return this.strings;
	}

	public Stream<String> stream() {
		return StreamUtils.createStream(new InnerIterator());
	}

	@Override
	public Iterator<String> iterator() {
		return new InnerIterator();
	}

	private String[] strings;
	private int[] marks;
	int minIndex = 0;
	int maxIndex = -1;

	private final boolean isValidIndex(int index) {
		return index >= this.minIndex && index <= this.maxIndex;
	}

	private final boolean checkValidIndex(int index) {
		if (index < minIndex || index > maxIndex) {
			throw new IndexOutOfBoundsException();
		}
		return true;
	}

	private final void init(String[] datas) {
		this.strings = datas;
		this.marks = new int[this.strings.length];
		for (int index = 0; index < this.marks.length; index++) {
			marks[index] = 0;
		}
		this.minIndex = 0;
		this.maxIndex = this.marks.length - 1;
	}

	private class InnerIterator implements Iterator<String> {
		public InnerIterator() {
			this.currentIndex = -1;
			this.nextIndex = this.findNext(this.currentIndex);
		}

		@Override
		public boolean hasNext() {
			return nextIndex >= 0;
		}

		@Override
		public String next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			this.currentIndex = this.nextIndex;
			this.nextIndex = this.findNext(this.nextIndex);
			return ListStringWithMark.this.strings[this.currentIndex];
		}

		private int currentIndex;
		private int nextIndex;

		private int findNext(int startIndex) {
			int next = startIndex;
			while (++next <= ListStringWithMark.this.marks.length - 1) {
				if (!isMark(next)) {
					return next;
				}
			}
			return -1;
		}
	}
}
