package com.bkav.struct;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.bkav.util.CollectionUtil;

public abstract class CommonMash implements Mash {

	public CommonMash(int length, byte mode) {
		if (length < 0 || length > MAX_INDEX) {
			throw new InvalidParameterException("Length must greater zero");
		}
		this.init(length, mode);
	}

	public CommonMash(int length) {
		this(length, NORMAL_MODE);
	}

	@Override
	public int length() {
		return this.maxIndex - this.minIndex + 1;
	}

	@Override
	public int maxIndex() {
		return this.maxIndex;
	}

	@Override
	public int minIndex() {
		return this.minIndex;
	}

	public final boolean isMark(int index) {
		this.checkValidIndex(index);
		return this.isMarkByIndex(index);
	}

	public final boolean isUnMark(int index) {
		this.checkValidIndex(index);
		return this.isUnmarkByIndex(index);
	}

	@Override
	public boolean[] markValue() {
		Boolean[] output = IntStream.rangeClosed(this.minIndex, this.maxIndex).mapToObj(this::isMark)
				.toArray(Boolean[]::new);
		return CollectionUtil.toArray(output);
	}

	@Override
	public void setMark(int... indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::markByIndex);
	}

	@Override
	public void setMark(Collection<Integer> indexs) {
		indexs.stream().filter(this::isValidIndex).forEach(this::markByIndex);
	}

	@Override
	public void setMarkWithRelativeIndex(int... relativeIndexs) {
		int[] unmarksIndex = this.unMarkIndexs();
		if (unmarksIndex.length == 0) {
			return;
		}
		Arrays.stream(relativeIndexs).filter(relativeIndex -> relativeIndex >= 0 && relativeIndex < unmarksIndex.length)
				.mapToObj(relativeIndex -> unmarksIndex[relativeIndex]).forEach(this::setMark);
	}

	@Override
	public void unMarkWithRelativeIndex(int... relativeIndexs) {
		int[] marksIndex = this.markIndexs();
		if (marksIndex.length == 0) {
			return;
		}
		Arrays.stream(relativeIndexs).filter(relativeIndex -> relativeIndex >= 0 && relativeIndex < marksIndex.length)
				.mapToObj(relativeIndex -> marksIndex[relativeIndex]).forEach(this::resetByIndex);
	}

	@Override
	public int[] markIndexs() {
		return this.markIndexStream().toArray();
	}

	@Override
	public IntStream markIndexStream() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex).filter(this::isMark);
	}

	@Override
	public Iterator<Integer> markInterator() {
		return markIndexStream().iterator();
	}

	@Override
	public int[] unMarkIndexs() {
		return this.unMarkIndexStream().toArray();
	}

	@Override
	public IntStream unMarkIndexStream() {
		return IntStream.rangeClosed(this.minIndex, this.maxIndex).filter(this::isUnMark);
	}

	@Override
	public Iterator<Integer> unMarkInterator() {
		return unMarkIndexStream().iterator();
	}

	@Override
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

	@Override
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

	@Override
	public void resetByIndex(int... indexs) {
		Arrays.stream(indexs).filter(this::isValidIndex).forEach(this::resetMark);
	}

	@Override
	public void resetByIndex(Collection<Integer> indexs) {
		indexs.stream().filter(this::isValidIndex).forEach(this::resetMark);
	}

	@Override
	public void reset() {
		this.init(this.maxIndex - this.minIndex + 1, this.config);
	}

	@Override
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
		return IntStream.rangeClosed(startInclusiveIndex, endInclusiveIndex).peek(consumer::accept).toArray();
	}

	@Override
	public int[] getFragmentIndex(int containIndex) {
		return this.getFragmentIndex(containIndex, new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
			}
		});
	}

	@Override
	public int[][] getFragmentsContainIndex(int... containIndexs) {
		return Arrays.stream(containIndexs).mapToObj(this::getFragmentIndex).toArray(int[][]::new);
	}

	@Override
	public int[][] getFragmentsContainIndex(Collection<Integer> containIndexs) {
		return containIndexs.stream().map(this::getFragmentIndex).toArray(int[][]::new);
	}

	@Override
	public int[][] getFragmentsContainIndexOptimal(int... containIndexs) {
		return Arrays.stream(containIndexs).distinct().mapToObj(this::getFragmentIndex)
				.filter(array -> array.length > 0).filter(CollectionUtil.distinctArrayInteger()).toArray(int[][]::new);
	}

	@Override
	public int[][] getFragmentsContainIndexOptimal(Collection<Integer> containIndexs) {
		List<Integer> starts = new ArrayList<>();
		List<Integer> ends = new ArrayList<>();
		return containIndexs.stream().distinct().filter(index -> {
			for (int i = 0; i < starts.size(); i++) {
				int start = starts.get(i);
				int end = ends.get(i);
				if (index >= start && index <= end) {
					return false;
				}
			}
			return true;
		}).map(this::getFragmentIndex).filter(array -> array.length > 0).peek(array -> {
			starts.add(array[0]);
			ends.add(array[array.length - 1]);
		}).toArray(int[][]::new);
	}

	@Override
	public void resetFragment(int containIndex) {
		this.checkValidIndex(containIndex);
		this.resetByIndex(this.getFragmentIndex(containIndex));
	}

	@Override
	public void resetFragments(int... containIndexs) {
		Arrays.stream(containIndexs).filter(this::isValidIndex).forEach(this::resetFragment);
	}

	@Override
	public void resetFragments(Collection<Integer> containIndexs) {
		containIndexs.stream().filter(this::isValidIndex).forEach(this::resetFragment);
	}

	@Override
	public void resetFragmentsOptimal(int... containIndexs) {
		Arrays.stream(containIndexs).distinct().filter(this::isValidIndex).filter(this::isMark)
				.forEach(this::resetFragment);
	}

	@Override
	public void resetFragmentsOptimal(Collection<Integer> containIndexs) {
		containIndexs.stream().distinct().filter(this::isValidIndex).filter(this::isMark).forEach(this::resetFragment);
	}

	@Override
	public int getFragmentEndIndexStartAt(int startIndexInclusive) {
		this.checkValidIndex(startIndexInclusive);
		int endIndexExclusive = IntStream.rangeClosed(startIndexInclusive, this.maxIndex).filter(this::isUnMark)
				.findFirst().orElse(this.maxIndex + 1);
		return startIndexInclusive == endIndexExclusive ? this.minIndex - 1 : endIndexExclusive - 1;
	}

	@Override
	public int[] getFragmentIndexStartAt(int startIndexInclusive) {
		int endIndexInclusive = this.getFragmentEndIndexStartAt(startIndexInclusive);
		return endIndexInclusive < this.minIndex ? new int[0]
				: IntStream.rangeClosed(startIndexInclusive, endIndexInclusive).toArray();
	}

	@Override
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

	@Override
	public int[] getFragmenIndextEndAt(int endIndexInclusive) {
		int startIndexInclusive = this.getFragmenStartIndextEndAt(endIndexInclusive);
		return startIndexInclusive < this.minIndex ? new int[0]
				: IntStream.range(startIndexInclusive, endIndexInclusive).toArray();
	}

	protected void init(int length, byte mode) {
		this.minIndex = 0;
		this.maxIndex = length - 1;
		this.createMarkArray(length);
	}

	protected abstract void createMarkArray(int length);
	
	protected abstract void resetMark(int index); 

	protected abstract void markByIndex(int index) ;
	/***
	 * Check valid <i>index</i>
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException
	 *             if <i>index</i> is invalid.
	 */
	protected final boolean checkValidIndex(int index) {
		if (index < minIndex || index > maxIndex) {
			int strict_mode = this.config & STRICT_MODE;
			if (strict_mode > 0) {
				throw new IndexOutOfBoundsException();
			}
			return false;
		}
		return true;
	}
	
	/***
	 * Check valid index without throw Exception.
	 * 
	 * @param index
	 * @return true if index valid or false if otherise
	 */
	protected final boolean isValidIndex(int index) {
		try {
			this.checkValidIndex(index);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	protected abstract boolean isMarkByIndex(int index);
	protected abstract boolean isUnmarkByIndex(int index);
	
	private int minIndex;
	private int maxIndex;
	private byte config;

}
