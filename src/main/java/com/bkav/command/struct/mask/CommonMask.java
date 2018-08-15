package com.bkav.command.struct.mask;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bkav.command.common.Consumer;
import com.bkav.command.common.Predicate;
import com.bkav.command.util.CollectionUtil;

/***
 * {@link CommonMask} implement basic method of {@link Mask}
 */
public abstract class CommonMask implements Mask {

	public CommonMask(int length, MaskConfig config) {
		if (length < 0 || length > MaskConfig.MAX_INDEX) {
			throw new InvalidParameterException("Length must greater zero");
		}
		this.init(length, config);
	}

	public CommonMask(int length) {
		this(length, MaskConfig.getDefaultConfig());
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

	@Override
	public boolean[] markValue() {
		boolean[] output = new boolean[this.maxIndex - this.minIndex + 1];
		for (int index = this.minIndex; index <= this.maxIndex; index++) {
			output[index - this.minIndex] = this.isMark(index);
		}
		return output;
	}

	@Override
	public void setMark(int... indexs) {
		for (int index : indexs) {
			if (this.isValidIndex(index)) {
				this.markByIndex(index);
			}
		}
	}

	@Override
	public void setMark(Collection<Integer> indexs) {
		for (Integer index : indexs) {
			if (this.isValidIndex(index)) {
				this.markByIndex(index);
			}
		}
	}

	@Override
	public void setMarkWithRelativeIndex(int... relativeIndexs) {
		int[] unmarksIndex = this.unMarkIndexs();
		if (unmarksIndex.length == 0) {
			return;
		}
		for (int relativeIndex : relativeIndexs) {
			if (relativeIndex >= 0 && relativeIndex < unmarksIndex.length) {
				this.setMark(unmarksIndex[relativeIndex]);
			}
		}
	}

	@Override
	public void setMarkWithRelativeIndex(Collection<Integer> relativeIndexs) {
		int[] unmarksIndex = this.unMarkIndexs();
		if (unmarksIndex.length == 0) {
			return;
		}
		for (int relativeIndex : relativeIndexs) {
			if (relativeIndex >= 0 && relativeIndex < unmarksIndex.length) {
				this.setMark(unmarksIndex[relativeIndex]);
			}
		}
	}

	@Override
	public void unMarkWithRelativeIndex(int... relativeIndexs) {
		int[] marksIndex = this.markIndexs();
		if (marksIndex.length == 0) {
			return;
		}
		for (int relativeIndex : relativeIndexs) {
			if (relativeIndex >= 0 && relativeIndex < marksIndex.length) {
				this.resetByIndex(marksIndex[relativeIndex]);
			}
		}
	}

	@Override
	public void unMarkWithRelativeIndex(Collection<Integer> relativeIndexs) {
		int[] marksIndex = this.markIndexs();
		if (marksIndex.length == 0) {
			return;
		}
		for (int relativeIndex : relativeIndexs) {
			if (relativeIndex >= 0 && relativeIndex < marksIndex.length) {
				this.resetByIndex(marksIndex[relativeIndex]);
			}
		}
	}

	@Override
	public int[] markIndexs() {
		List<Integer> indexs = new ArrayList<>();
		for (int index = this.minIndex; index <= this.maxIndex; index++) {
			if (this.isMark(index)) {
				indexs.add(index);
			}
		}
		int[] output = new int[indexs.size()];
		for (int index = 0; index < output.length; index++) {
			output[index] = indexs.get(index);
		}
		return output;
	}

	@Override
	public Iterator<Integer> markInterator() {
		List<Integer> indexs = new ArrayList<>();
		for (int index = this.minIndex; index <= this.maxIndex; index++) {
			if (this.isMark(index)) {
				indexs.add(index);
			}
		}
		return indexs.iterator();
	}

	@Override
	public int[] unMarkIndexs() {
		List<Integer> indexs = new ArrayList<>();
		for (int index = this.minIndex; index <= this.maxIndex; index++) {
			if (this.isUnMark(index)) {
				indexs.add(index);
			}
		}
		int[] output = new int[indexs.size()];
		for (int index = 0; index < output.length; index++) {
			output[index] = indexs.get(index);
		}
		return output;
	}

	@Override
	public Iterator<Integer> unMarkInterator() {
		List<Integer> indexs = new ArrayList<>();
		for (int index = this.minIndex; index <= this.maxIndex; index++) {
			if (this.isUnMark(index)) {
				indexs.add(index);
			}
		}
		return indexs.iterator();
	}

	@Override
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

	@Override
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

	@Override
	public void resetByIndex(int... indexs) {
		for (int index : indexs) {
			if (this.isValidIndex(index)) {
				this.resetMark(index);
			}
		}
	}

	@Override
	public void resetByIndex(Collection<Integer> indexs) {
		for (int index : indexs) {
			if (this.isValidIndex(index)) {
				this.resetMark(index);
			}
		}
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
		int[] output = new int[endInclusiveIndex - startInclusiveIndex + 1];
		for (int index = 0; index < output.length; index++) {
			consumer.accept(index);
			output[index] = index + startInclusiveIndex;
		}
		return output;
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
		int[][] output = new int[containIndexs.length][];
		int index = -1;
		for (int containIndex : containIndexs) {
			int[] fragmentIndex = this.getFragmentIndex(containIndex);
			output[++index] = fragmentIndex;
		}
		return output;
	}

	@Override
	public int[][] getFragmentsContainIndex(Collection<Integer> containIndexs) {
		int[][] output = new int[containIndexs.size()][];
		int index = -1;
		for (int containIndex : containIndexs) {
			int[] fragmentIndex = this.getFragmentIndex(containIndex);
			output[index++] = fragmentIndex;
		}
		return output;
	}

	@Override
	public int[][] getFragmentsContainIndexOptimal(int... containIndexs) {
		Set<Integer> indexs = new HashSet<>();
		for (int index : containIndexs) {
			indexs.add(index);
		}
		List<int[]> listOutput = new ArrayList<>();
		Predicate<int[]> validCheck = CollectionUtil.distinctArrayInteger();
		for (int index : indexs) {
			int[] fragment = this.getFragmentIndex(index);
			if (fragment.length <= 0) {
				continue;
			}
			if (validCheck.test(fragment)) {
				listOutput.add(fragment);
			}
		}
		return listOutput.toArray(new int[listOutput.size()][]);
	}

	@Override
	public int[][] getFragmentsContainIndexOptimal(Collection<Integer> containIndexs) {
		List<Integer> starts = new ArrayList<>();
		List<Integer> ends = new ArrayList<>();
		Set<Integer> indexs = new HashSet<>();
		for (int index : containIndexs) {
			indexs.add(index);
		}
		List<int[]> fragments = new ArrayList<>();
		for (int index : indexs) {
			boolean isContains = false;
			for (int i = 0; i < starts.size(); i++) {
				int start = starts.get(i);
				int end = ends.get(i);
				if (index >= start && index <= end) {
					isContains = true;
					continue;
				}
			}
			if (isContains) {
				continue;
			}
			int[] fragment = this.getFragmentIndex(index);
			if (fragment.length == 0) {
				continue;
			}
			starts.add(fragment[0]);
			ends.add(fragment[fragment.length - 1]);
			fragments.add(fragment);
		}
		return fragments.toArray(new int[fragments.size()][]);
	}

	@Override
	public void resetFragment(int containIndex) {
		this.checkValidIndex(containIndex);
		this.resetByIndex(this.getFragmentIndex(containIndex));
	}

	@Override
	public void resetFragments(int... containIndexs) {
		for (int containIndex : containIndexs) {
			if (this.isValidIndex(containIndex)) {
				this.resetFragment(containIndex);
			}
		}
	}

	@Override
	public void resetFragments(Collection<Integer> containIndexs) {
		for (int containIndex : containIndexs) {
			if (this.isValidIndex(containIndex)) {
				this.resetFragment(containIndex);
			}
		}
	}

	@Override
	public void resetFragmentsOptimal(int... containIndexs) {
		Set<Integer> indexs = new HashSet<>();
		for (int index : containIndexs) {
			indexs.add(index);
		}
		for (int index : indexs) {
			if (!this.isValidIndex(index)) {
				continue;
			}
			if (this.isMark(index)) {
				this.resetFragment(index);
			}

		}
	}

	@Override
	public void resetFragmentsOptimal(Collection<Integer> containIndexs) {
		Set<Integer> indexs = new HashSet<>();
		for (int index : containIndexs) {
			indexs.add(index);
		}
		for (int index : indexs) {
			if (!this.isValidIndex(index)) {
				continue;
			}
			if (this.isMark(index)) {
				this.resetFragment(index);
			}
		}
	}

	@Override
	public int getFragmentEndIndexStartAt(int startIndexInclusive) {
		this.checkValidIndex(startIndexInclusive);
		int endIndexExclusive = -1;
		for (int index = startIndexInclusive; index <= this.maxIndex; index++) {
			if (this.isUnMark(index)) {
				endIndexExclusive = index;
				break;
			}
		}
		if (endIndexExclusive < 0) {
			endIndexExclusive = this.maxIndex + 1;
		}

		return startIndexInclusive == endIndexExclusive ? this.minIndex - 1 : endIndexExclusive - 1;
	}

	@Override
	public int[] getFragmentIndexStartAt(int startIndexInclusive) {
		int endIndexInclusive = this.getFragmentEndIndexStartAt(startIndexInclusive);

		if (endIndexInclusive < this.minIndex) {
			return new int[0];
		}
		int[] output = new int[endIndexInclusive - startIndexInclusive + 1];
		for (int index = 0; index < output.length; index++) {
			output[index] = index + startIndexInclusive;
		}
		return output;
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
		if (startIndexInclusive < this.minIndex) {
			return new int[0];
		}
		int[] output = new int[endIndexInclusive - startIndexInclusive];
		for (int index = 0; index < output.length; index++) {
			output[index] = index + startIndexInclusive;
		}
		return output;
	}

	public void setConfig(MaskConfig config) {
		this.config = config;
	}

	public MaskConfig config() {
		return this.config;
	}

	/***
	 * Check valid <i>index</i>
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException
	 *             if <i>index</i> is invalid when config mode in
	 *             {@link Mask#STRICT_MODE}.
	 */
	public final boolean checkValidIndex(int index) {
		if (this.isValidIndex(index)) {
			return true;
		}
		if (this.config.isStrictMode()) {
			throw new IndexOutOfBoundsException();
		}
		return false;
	}

	/***
	 * Check valid index without throw Exception.
	 * 
	 * @param index
	 * @return true if index valid or false if otherise
	 */
	public final boolean isValidIndex(int index) {
		return index >= this.minIndex && index <= this.maxIndex;
	}

	@Override
	public String toString() {
		return String.format("%s [minIndex=%s, maxIndex=%s, config=%s]", this.getClass().getSimpleName(), this.minIndex,
				this.maxIndex, this.config);
	}

	protected abstract void createMarkArray(int length);

	protected abstract void resetMark(int index);

	protected abstract void markByIndex(int index);

	protected int minIndex;
	protected int maxIndex;
	protected MaskConfig config;

	protected void init(int length, MaskConfig config) {
		this.minIndex = 0;
		this.maxIndex = length - 1;
		this.config = config;
		this.createMarkArray(length);
	}
}
