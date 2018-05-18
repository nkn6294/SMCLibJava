package com.bkav.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
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

	public Boolean isMark(int index) {
		this.checkValidIndex(index);
		return this.marks[index] > 0;
	}
	
	public Boolean isUnMark(int index) {
		this.checkValidIndex(index);
		return this.marks[index] <= 0;
	}

	public void setMark(int... indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::markByIndex);
	}

	public int[] marks() {
		return this.marks;
	}
	
	public int getMark(int index) {
		this.checkValidIndex(index);
		return this.marks[index];
	}
	
	public Boolean[] markValue() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex)
				.mapToObj(this::isMark).toArray(Boolean[]::new);
	}
	
	public Integer[] markIndexs() {
		return this.markStream().toArray(Integer[]::new);
	}
	
	public Integer[] unMarkIndexs() {
		return this.unMarkStream().toArray(Integer[]::new);
	}
	
	public String[][] getMarks() {
		List<String[]> result = new ArrayList<>();
		List<String> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex; index++) {
			if (this.isMark(index)) {
				item.add(this.strings[index]);
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
		for (int index = 0; index <= this.maxIndex; index++) {
			if (!this.isMark(index)) {
				item.add(this.strings[index]);
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
			Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::resetMark);
		}
	}

	public String[] strings() {
		return this.strings;
	}

	public Stream<String> stream() {
		return StreamUtils.createStream(new InnerIterator());
	}

	public Stream<String> markStream() {
		return IntStream.rangeClosed(0, this.maxIndex)	
				.filter(this::isMark).mapToObj(this::get);
	}
	
	public Stream<String> unMarkStream() {
		return IntStream.rangeClosed(0, this.maxIndex)
				.filter(this::isUnMark).mapToObj(this::get);
	}
	
	@Override
	public Iterator<String> iterator() {
		return new InnerIterator();
	}

	private String[] strings;
	private int[] marks;
	private int minIndex = 0;
	private int maxIndex = -1;

	private void resetMark(int index) {
		this.marks[index] = 0;
	}
	
	private void markByIndex(int index) {
		this.marks[index] += 1;
	}
	
	private final boolean isValidIndex(int index) {
		try {
			this.checkValidIndex(index);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	private final void checkValidIndex(int index) {
		if (index < minIndex || index > maxIndex) {
			throw new IndexOutOfBoundsException();
		}
	}

	private final void init(String[] datas) {
		this.strings = datas;
		this.marks = new int[this.strings.length];
		this.minIndex = 0;
		this.maxIndex = this.marks.length - 1;
		IntStream.rangeClosed(this.minIndex, this.maxIndex).forEach(this::resetMark);
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
			while (++next <= ListStringWithMark.this.maxIndex) {
				if (!ListStringWithMark.this.isMark(next)) {
					return next;
				}
			}
			return -1;
		}
	}
}
