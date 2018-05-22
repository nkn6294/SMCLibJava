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

import com.bkav.util.CollectionUtil;
import com.bkav.util.StreamUtils;

public class ListStringWithMark implements Iterable<String> {
	//TODO viet rieng mash ra lop khac.
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
			.mapToObj(relativeIndex -> relativeIndexs[relativeIndex])
			.forEach(this::setMark);
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
			} else if (item.size() > 0) {
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
			} else if (item.size() > 0) {
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
			} else if (item.size() > 0) {
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
			} else if (item.size() > 0) {
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
		return this.getFragmentIndex(containIndex, new  Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
			}
		});
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
		return String.format("%s [strings=%s, marks=%s, minIndex=%s, maxIndex=%s]",
				this.getClass().getSimpleName(), Arrays.toString(strings), Arrays.toString(marks), minIndex, maxIndex);
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
	
	/***
	 * Check valid <i>index</i>
	 * @param index
	 * @throws IndexOutOfBoundsException if <i>index</i> is invalid.
	 */
	private final void checkValidIndex(int index) {
		if (index < minIndex || index > maxIndex) {
			throw new IndexOutOfBoundsException();
		}
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
	 * Fragment: <b><i>Continuity</i></b> words  marked in string arrays with <i>startIndex</i>, <i>endIndex</i> and origin data <i>datas</i>
	 */
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
