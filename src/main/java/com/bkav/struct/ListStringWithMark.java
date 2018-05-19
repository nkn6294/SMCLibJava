package com.bkav.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bkav.SystemManager;
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
	public String get(Integer index) {
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

	public void setMark(Integer[] indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::markByIndex);
	}

	public void setMark(Collection<Integer> indexs) {
		indexs.stream().filter(this::isValidIndex).forEach(this::markByIndex);
	}

	public int[] marks() {
		return this.marks;
	}

	public int getMark(int index) {
		this.checkValidIndex(index);
		return this.marks[index];
	}

	public Boolean[] markValue() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex).mapToObj(this::isMark).toArray(Boolean[]::new);
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

	public Integer[][] getMarkIndexs() {
		List<Integer[]> result = new ArrayList<>();
		List<Integer> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex; index++) {
			if (this.isMark(index)) {
				item.add(index);
			} else if (item.size() > 0) {
				result.add(item.toArray(new Integer[item.size()]));
				item.clear();
			}
		}
		return result.toArray(new Integer[result.size()][]);
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

	public Integer[][] getUnMarkIndexs() {
		List<Integer[]> result = new ArrayList<>();
		List<Integer> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex; index++) {
			if (!this.isMark(index)) {
				item.add(index);
			} else if (item.size() > 0) {
				result.add(item.toArray(new Integer[item.size()]));
				item.clear();
			}
		}
		return result.toArray(new Integer[result.size()][]);
	}

	public void resetByIndex(int... indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::resetMark);
	}

	public void resetByIndex(Integer[] indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::resetMark);
	}

	public void resetByIndex(Collection<Integer> indexs) {
		indexs.stream().filter(this::isValidIndex).forEach(this::resetMark);
	}

	public void reset() {
		this.init(this.strings);
	}

	public String[] getFragment(int containIndex) {
		this.checkValidIndex(containIndex);
		return Arrays.stream(this.getFragmentIndex(containIndex))
				.mapToObj(this::get).toArray(String[]::new);//or using with consumer runtime
	}

	public int[] getFragmentIndex(int containIndex, Consumer<Integer> consumer) {
		SystemManager.logger.info(">" + containIndex);
		int startInclusiveIndex = this.getFragmenStartIndextEndAt(containIndex);
		if (startInclusiveIndex < this.minIndex) {
			return new int[0];
		}
		int endInclusiveIndex = this.getFragmentEndIndexStartAt(containIndex + 1);
		if (endInclusiveIndex < this.minIndex) {
			endInclusiveIndex = containIndex;
		}
		return IntStream.rangeClosed(startInclusiveIndex,  endInclusiveIndex)
				.peek(consumer::accept).toArray();
	}

	public int[] getFragmentIndexWithIntConsumer(int containIndex, IntConsumer consumer) { 
		return getFragmentIndex(containIndex, consumer::accept);
	}
	public int[] getFragmentIndex(int containIndex) {
		return this.getFragmentIndexWithIntConsumer(containIndex, new IntConsumer() {
			@Override
			public void accept(int value) {
			}
		});
	}

	public int[][] getFragments(int... containIndexs) {
		List<Integer> starts = new ArrayList<>();
		List<Integer> ends = new ArrayList<>();
		
		for (int index = this.minIndex; index <= this.maxIndex; index++) {
			
			//TODO build fragments start /end -> list
		}
		
		List<int[]> fragments = new ArrayList<>();
		for (int index = 0; index < starts.size(); index++) {
			fragments.add(IntStream.rangeClosed(starts.get(index).intValue(), ends.get(index).intValue()).toArray());
		}
		return fragments.toArray(new int[fragments.size()][]);
	}

	public int[][] getFragments(Integer[] containIndexs) {
		//TODO getFragments Index array
		return null;
	}

	public int[][] getFragments(Collection<Integer> containIndexs) {
		//TODO getFragments Index collection
		return null;
	}

	public void resetFragment(int containIndex) {
		this.checkValidIndex(containIndex);
		this.resetByIndex(this.getFragmentIndex(containIndex));
	}

	public void resetFragments(int... containIndexs) {
		//TODO resetFragments with varargs
	}

	public void resetFragments(Integer[] containIndexs) {
		//TODO resetFragments with array
	}

	public void resetFragments(Collection<Integer> containIndexs) {
		//TODO resetFragments with collection
	}

	public String[] strings() {
		return this.strings;
	}

	public Stream<String> stream() {
		return StreamUtils.createStream(new InnerIterator());
	}

	public Stream<String> markStream() {
		return IntStream.rangeClosed(0, this.maxIndex).filter(this::isMark).mapToObj(this::get);
	}

	public Stream<String> unMarkStream() {
		return IntStream.rangeClosed(0, this.maxIndex).filter(this::isUnMark).mapToObj(this::get);
	}

	@Override
	public Iterator<String> iterator() {
		return new InnerIterator();
	}

	public int getFragmentEndIndexStartAt(int startIndexInclusive) {
		this.checkValidIndex(startIndexInclusive);
		int endIndexExclusive = IntStream.rangeClosed(startIndexInclusive, this.maxIndex)
				.filter(this::isUnMark)
				.findFirst().orElse(this.maxIndex + 1);
		return startIndexInclusive == endIndexExclusive ? this.minIndex - 1 : endIndexExclusive - 1; 
	}
	public int[] getFragmentIndexStartAt(int startIndexInclusive) {
		int endIndexInclusive = this.getFragmentEndIndexStartAt(startIndexInclusive);
		return endIndexInclusive < this.minIndex ? new int[0] :
			IntStream.rangeClosed(startIndexInclusive, endIndexInclusive).toArray();
	}
	
	public String[] getFragmentStartAt(int startIndexInclusive) {
		int endIndexInclusive = this.getFragmentEndIndexStartAt(startIndexInclusive);
		return endIndexInclusive < this.minIndex ? new String[0] :
			IntStream.rangeClosed(startIndexInclusive, endIndexInclusive)
				.mapToObj(this::get).toArray(String[]::new);
	}

	public int getFragmenStartIndextEndAt(int endIndexInclusive) {
		this.checkValidIndex(endIndexInclusive);
		int startExclusiveIndex = this.minIndex - 1;
		for (int index = endIndexInclusive; index >= this.minIndex; index--) {
			if (this.isUnMark(index)) {
				startExclusiveIndex = index;
			}
		}
		return startExclusiveIndex == endIndexInclusive ? this.minIndex - 1 : startExclusiveIndex + 1;
	}
	
	public int[] getFragmenIndextEndAt(int endIndexInclusive) {
		int startIndexInclusive = this.getFragmenStartIndextEndAt(endIndexInclusive);
		return startIndexInclusive < this.minIndex ? new int[0] : IntStream.range(startIndexInclusive, endIndexInclusive).toArray();
	}
	
	public String[] getFragmenEndAt(int endIndexInclusive) {
		int startIndexInclusive = this.getFragmenStartIndextEndAt(endIndexInclusive);
		return startIndexInclusive < this.minIndex ? new String[0] : 
			IntStream.range(startIndexInclusive, endIndexInclusive).mapToObj(this::get).toArray(String[]::new);
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

	public class Fragment {
		public int startIndex;
		public int endIndex;
		public String[] datas;
		
		public Fragment(String[] datas, int startIndex, int endIndex) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.datas = datas;
		}
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
