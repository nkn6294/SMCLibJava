package com.bkav.command.struct;

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

import com.bkav.command.util.CollectionUtil;
import com.bkav.command.util.StreamUtils;

public class ListStringWithMask2 implements Iterable<String> {
	
	public static final byte NORMAL_MODE = 1 << 0;
	public static final byte STRICT_MODE = 1 << 1;
	
	public ListStringWithMask2(String[] strings) {
		this.init(strings);
	}
	public int length() {
		return this.strings.length;
	}
	
	public int minIndex() {
		return this.minIndex;
	}
	
	public int maxIndex() {
		return this.maxIndex;
	}
	public String get(int index) {
		this.checkValidIndex(index);
		return this.strings[index];
	}
	public boolean isMark(int index) {
		this.checkValidIndex(index);
		return this.marks[index] > 0;
	}

	public boolean isUnMark(int index) {
		this.checkValidIndex(index);
		return this.marks[index] <= 0;
	}

	public void setMark(int... indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::markByIndex);
	}

	public void setMarkWithRelativeIndex(int... relativeIndexs) {
		int[] unmarksIndex = this.unMarkIndexs();
		if (unmarksIndex.length == 0) {
			return;
		}
		Arrays.stream(relativeIndexs)
			.filter(relativeIndex -> relativeIndex >= 0 && relativeIndex < unmarksIndex.length)
			.mapToObj(relativeIndex -> unmarksIndex[relativeIndex])
			.forEach(this::setMark);
	}
	
	public void unMarkWithRelativeIndex(int... relativeIndexs) {
		int[] marksIndex = this.markIndexs();
		if (marksIndex.length == 0) {
			return;
		}
		Arrays.stream(relativeIndexs)
			.filter(relativeIndex -> relativeIndex >= 0 && relativeIndex < marksIndex.length)
			.mapToObj(relativeIndex -> marksIndex[relativeIndex])
			.forEach(this::resetByIndex);
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
	
	public boolean[] markValue() {
		Boolean[] output = IntStream.rangeClosed(this.minIndex, this.maxIndex).mapToObj(this::isMark).toArray(Boolean[]::new);
		return CollectionUtil.toArray(output);
	}
	
	public int[] markIndexs() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex)
			.filter(this::isMark).toArray();
	}

	public int[] unMarkIndexs() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex)
				.filter(this::isUnMark).toArray();
	}

	public String[][] getFragments() {
		List<String[]> result = new ArrayList<>();
		List<String> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex; index++) {
			if (this.isMark(index)) {
				item.add(this.strings[index]);
			} else if (!item.isEmpty()) {
				result.add(item.toArray(new String[item.size()]));
				item.clear();
			}
		}
		return result.toArray(new String[result.size()][]);
	}

	public int[][] getFragmentIndexs() {
		List<Integer[]> result = new ArrayList<>();
		List<Integer> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex; index++) {
			if (this.isMark(index)) {
				item.add(index);
			} else if (!item.isEmpty()) {
				result.add(item.toArray(new Integer[item.size()]));
				item.clear();
			}
		}
		return result.toArray(new int[result.size()][]);
	}

	public String[][] getUnMarks() {
		List<String[]> result = new ArrayList<>();
		List<String> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex; index++) {
			if (!this.isMark(index)) {
				item.add(this.strings[index]);
			} else if (!item.isEmpty()) {
				result.add(item.toArray(new String[item.size()]));
				item.clear();
			}
		}
		return result.toArray(new String[result.size()][]);
	}

	public int[][] getUnMarkIndexs() {
		List<Integer[]> result = new ArrayList<>();
		List<Integer> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex; index++) {
			if (!this.isMark(index)) {
				item.add(index);
			} else if (!item.isEmpty()) {
				result.add(item.toArray(new Integer[item.size()]));
				item.clear();
			}
		}
		Integer[][] output = result.toArray(new Integer[result.size()][]);
		return CollectionUtil.toArray(output);
	}

	public void resetByIndex(int... indexs) {
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
		int startInclusiveIndex = this.getFragmenStartIndextEndAt(containIndex);
		if (startInclusiveIndex < this.minIndex) {
			return new int[0];
		}
		int endInclusiveIndex = this.maxIndex;
		if (containIndex < this.maxIndex) {
			endInclusiveIndex = this.getFragmentEndIndexStartAt(containIndex + 1);			
		}
		if (endInclusiveIndex < this.minIndex) {
			endInclusiveIndex = containIndex;
		}
		return IntStream.rangeClosed(startInclusiveIndex,  endInclusiveIndex)
				.peek(consumer::accept)
				.toArray();
	}

	public int[] getFragmentIndexWithIntConsumer(int containIndex, IntConsumer consumer) { 
		return this.getFragmentIndex(containIndex, consumer::accept);
	}
	public int[] getFragmentIndex(int containIndex) {
		return this.getFragmentIndex(containIndex, t -> {});
	}

	public int[][] getFragmentsContainIndex(int... containIndexs) {
		return Arrays.stream(containIndexs).mapToObj(this::getFragmentIndex).toArray(int[][]::new);
	}

	public int[][] getFragmentsContainIndex(Collection<Integer> containIndexs) {
		return containIndexs.stream().map(this::getFragmentIndex).toArray(int[][]::new);
	}
	public int[][] getFragmentsContainIndexOptimal(int... containIndexs) {
		return Arrays.stream(containIndexs)
					.distinct()
					.mapToObj(this::getFragmentIndex)
					.filter(array -> array.length > 0)
					.filter(CollectionUtil.distinctArrayInteger())
					.toArray(int[][]::new);
	}

	public int[][] getFragmentsContainIndexOptimal(Collection<Integer> containIndexs) {
		List<Integer> starts = new ArrayList<>();
		List<Integer> ends = new ArrayList<>();
		return containIndexs.stream()
			.distinct()
			.filter(index -> {
				for (int i = 0; i < starts.size(); i++) {
					int start = starts.get(i);
					int end = ends.get(i);
					if (index >= start && index <= end) {
						return false;
					}
				}
				return true;
			})
			.map(this::getFragmentIndex)
			.filter(array -> array.length > 0)
			.peek(array -> {
				starts.add(array[0]);
				ends.add(array[array.length - 1]);
			})
			.toArray(int[][]::new);
	}
	
	public void resetFragment(int containIndex) {
		this.checkValidIndex(containIndex);
		this.resetByIndex(this.getFragmentIndex(containIndex));
	}

	public void resetFragments(int... containIndexs) {
		Arrays.stream(containIndexs).filter(this::isValidIndex).forEach(this::resetFragment);
	}

	public void resetFragments(Collection<Integer> containIndexs) {
		containIndexs.stream().filter(this::isValidIndex).forEach(this::resetFragment);
	}
	public void resetFragmentsOptimal(int... containIndexs) {
		Arrays.stream(containIndexs)
			.distinct()
			.filter(this::isValidIndex)
			.filter(this::isMark)
			.forEach(this::resetFragment);
	}

	public void resetFragmentsOptimal(Collection<Integer> containIndexs) {
		containIndexs.stream()
			.distinct()
			.filter(this::isValidIndex)
			.filter(this::isMark)
			.forEach(this::resetFragment);
	}

	public String[] strings() {
		return this.strings;
	}

	public String[] marksString() {
		return this.markStream().toArray(String[]::new);
	}
	
	public String[] unMarkString() {
		return this.unMarkStream().toArray(String[]::new);
	}
	
	public Stream<String> stream() {
		return StreamUtils.createStream(new InnerIterator());
	}

	public Stream<String> markStream() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex).filter(this::isMark).mapToObj(this::get);
	}

	public Stream<String> unMarkStream() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex).filter(this::isUnMark).mapToObj(this::get);
	}

	@Override
	public Iterator<String> iterator() {
		return new InnerIterator();
	}
	/***
	 * Get <i>endIndex (Inclusive)</i> of fragment start with <i>startIndexInclusive</i>
	 * @param startIndexInclusive
	 * @return value of endIndex if exited fragment or <i>minIndex - 1</i> otherise.
	 * @throws IndexOutOfBoundsException if <i>startIndexInclusive</i> invalid.
	 */
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
				break;
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
	
	@Override
	public String toString() {
		return String.format("%s [strings=%s, marks=%s, minIndex=%s, maxIndex=%s, remains=%s]",
				this.getClass().getSimpleName(), Arrays.toString(strings), Arrays.toString(marks), minIndex, maxIndex, Arrays.toString(this.unMarkString()));
	}

	public int config() {
		return this.config;
	}
	
	public void setConfig(byte config) {
		this.config = config;
	}
	
	private String[] strings;
	private int[] marks;
	private int minIndex = 0;
	private int maxIndex = -1;
	private byte config = NORMAL_MODE;
	
	private void resetMark(int index) {
		this.marks[index] = 0;
	}

	private void markByIndex(int index) {
		this.marks[index] += 1;
	}
	
	/***
	 * Check valid <i>index</i>
	 * @param index
	 * @throws IndexOutOfBoundsException if <i>index</i> is invalid.
	 */
	private final boolean checkValidIndex(int index) {
		if (index < minIndex || index > maxIndex) {
			int strictMode = this.config & STRICT_MODE;
			if (strictMode > 0) {
				throw new IndexOutOfBoundsException();
			}
			return false;
		}
		return true;
	}
	/***
	 * Check valid index without throw Exception.
	 * @param index
	 * @return true if index valid or false if otherise
	 */
	private final boolean isValidIndex(int index) {
		try {
			this.checkValidIndex(index);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	/***
	 * Init with word array, marks status, <i>minIndex</i>, <i>maxIndex</i>
	 * @param datas Input data
	 */
	private final void init(String[] datas) {
		this.strings = datas;
		this.marks = new int[this.strings.length];
		this.minIndex = 0;
		this.maxIndex = this.marks.length - 1;
		IntStream.rangeClosed(this.minIndex, this.maxIndex).forEach(this::resetMark);
	}

	/***
	 * Inner interator not marked in orgin string array input.
	 *
	 */
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
			return strings[this.currentIndex];
		}

		private int currentIndex;
		private int nextIndex;

		private int findNext(int startIndex) {
			int next = startIndex;
			while (++next <= maxIndex) {
				if (!isMark(next)) {
					return next;
				}
			}
			return -1;
		}
	}
}
