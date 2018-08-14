package com.bkav.command.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class DatasWithMask<T> extends MaskWrapper implements Iterable<T> {
	
	public DatasWithMask(T[] datas, MaskConfig config) {
		super(datas.length, config);
		this.init(datas, config);
	}
	public DatasWithMask(T[] strings) {
		super(strings.length);
		this.init(strings, MaskConfig.getDefaultConfig());
	}
	
	public T get(int index) {
		this.checkValidIndex(index);
		return this.datas[index];
	}

	public List<List<T>> getFragments() {
		List<List<T>> result = new ArrayList<>();
		List<T> temp = new ArrayList<>();
		for (int index = 0; index <= maxIndex(); index++) {
			if (this.isMark(index)) {
				temp.add(this.datas[index]);
			} else if (!temp.isEmpty()) {
				List<T> item = new ArrayList<>();
				for (T t : temp) {
					item.add(t);
				}
				result.add(item);
				temp.clear();
			}
		}
		if (!temp.isEmpty()) {
			List<T> item = new ArrayList<>();
			for (T t : temp) {
				item.add(t);
			}
			result.add(item);
			temp.clear();
		}
		return result;
	}

	public List<List<T>> getUnMarks() {
		List<List<T>> result = new ArrayList<>();
		List<T> temp = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex(); index++) {
			if (!this.isMark(index)) {
				temp.add(this.datas[index]);
			} else if (!temp.isEmpty()) {
				List<T> item = new ArrayList<>();
				for (T t : temp) {
					item.add(t);
				}
				result.add(item);
				temp.clear();
			}
		}
		if (!temp.isEmpty()) {
			List<T> item = new ArrayList<>();
			for (T t : temp) {
				item.add(t);
			}
			result.add(item);
			temp.clear();
		}
		return result;
	}
	public List<T> getFragment(int containIndex) {
		this.checkValidIndex(containIndex);
		List<T> output = new ArrayList<>();
		for (int index : this.getFragmentIndex(containIndex)) {
			output.add(this.get(index));
		}
		return output;//or using with consumer runtime
	}

	public T[] datas() {
		return this.datas;
	}

	public List<T> marksValue() {
		List<T> output = new ArrayList<>();
		for (int index : this.markIndexs()) {
			output.add(this.get(index));
		}
		return output;
	}
	
	public List<T> unMarkValue() {
		List<T> output = new ArrayList<>();
		for (int index : this.unMarkIndexs()) {
			output.add(this.get(index));
		}
		return output;
	}
	public String[] markString() {
		List<T> marksValue = this.marksValue();
		String[] output = new String[marksValue.size()];
		for (int index = 0; index < output.length; index++) {
			output[index] = marksValue.get(index).toString();
		}
		return output;
	}
	public String[] unMarkString() {
		List<T> marksValue = this.unMarkValue();
		String[] output = new String[marksValue.size()];
		for (int index = 0; index < output.length; index++) {
			output[index] = marksValue.get(index).toString();
		}
		return output;
	}
	@Override
	public Iterator<T> iterator() {
//		return new InnerIterator();
		return this.unMarkValue().iterator();
	}

	public List<T> getFragmentStartAt(int startIndexInclusive) {
		int endIndexInclusive = this.getFragmentEndIndexStartAt(startIndexInclusive);
		if (endIndexInclusive < minIndex()) {
			return Collections.emptyList();
		}
		List<T> output = new ArrayList<>();
		for (int index = startIndexInclusive; index <= endIndexInclusive; index++) {
			output.add(this.get(index));
		}
		return output;
	}

	public List<T> getFragmenEndAt(int endIndexInclusive) {
		int startIndexInclusive = this.getFragmenStartIndextEndAt(endIndexInclusive);
		if (startIndexInclusive < minIndex()) {
			return Collections.emptyList(); 
		}
		List<T> output = new ArrayList<>();
		for (int index = startIndexInclusive; index < endIndexInclusive; index++) {
			output.add(this.get(index));
		}
		return output;
	}
	
	@Override
	public String toString() {
		return String.format("%s [datas=%s, mash=%s, remains=%s]", this.getClass().getSimpleName(), Arrays.toString(datas), mash.toString(), Arrays.toString(this.unMarkString()));
	}

	protected T[] datas;
	
	/***
	 * Init with word array, mash
	 * @param datas Input data
	 */
	protected final void init(T[] datas, MaskConfig config) {
		this.datas = datas;
		this.createMask(datas.length, config);
	}

	/***
	 * Inner interator not marked in orgin string array input.
	 */
	public class InnerIterator implements Iterator<T> {
		public InnerIterator() {
			this.currentIndex = -1;
			this.nextIndex = this.findNext(this.currentIndex);
		}

		@Override
		public boolean hasNext() {
			return nextIndex >= 0;
		}

		@Override
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			this.currentIndex = this.nextIndex;
			this.nextIndex = this.findNext(this.nextIndex);
			return get(this.currentIndex);
		}

		private int currentIndex;
		private int nextIndex;

		private int findNext(int startIndex) {
			int next = startIndex;
			while (++next <= maxIndex()) {
				if (!isMark(next)) {
					return next;
				}
			}
			return -1;
		}

		@Override
		public void remove() {
		}
	}
}
