package com.bkav.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bkav.util.StreamUtils;

public abstract class DatasWithMash<T> extends MashWrapper implements Iterable<T> {
	
	public DatasWithMash(T[] strings, byte config) {
		super(config);
		this.init(strings, config);
	}
	public DatasWithMash(T[] strings) {
		super();
		this.init(strings, Mash.NORMAL_MODE);
	}
	
	public T get(int index) {
		this.checkValidIndex(index);
		return this.datas[index];
	}

//	public int[] marks() {
//		return this.marks;
//	}
//
//	public int getMark(int index) {
//		this.checkValidIndex(index);
//		return this.marks[index];
//	}

	public List<List<T>> getFragments() {
		List<List<T>> result = new ArrayList<>();
		List<T> item = new ArrayList<>();
		for (int index = 0; index <= maxIndex(); index++) {
			if (this.isMark(index)) {
				item.add(this.datas[index]);
			} else if (!item.isEmpty()) {
				result.add(item);
				item.clear();
			}
		}
		return result;
	}

	public List<List<T>> getUnMarks() {
		List<List<T>> result = new ArrayList<>();
		List<T> item = new ArrayList<>();
		for (int index = 0; index <= this.maxIndex(); index++) {
			if (!this.isMark(index)) {
				item.add(this.datas[index]);
			} else if (!item.isEmpty()) {
				result.add(item);
				item.clear();
			}
		}
		return result;
	}
	public List<T> getFragment(int containIndex) {
		this.checkValidIndex(containIndex);
		return Arrays.stream(this.getFragmentIndex(containIndex))
				.mapToObj(this::get).collect(Collectors.toList());//or using with consumer runtime
	}

	
	public int[] getFragmentIndexWithIntConsumer(int containIndex, IntConsumer consumer) { 
		return this.getFragmentIndex(containIndex, consumer::accept);
	}

	public T[] datas() {
		return this.datas;
	}

	public List<T> marksValue() {
		return this.markStream().collect(Collectors.toList());
	}
	
	public List<T> unMarkValue() {
		return this.unMarkStream().collect(Collectors.toList());
	}
	public String[] markString() {
		return this.markStream().toArray(String[]::new);
	}
	public String[] unMarkString() {
		return this.unMarkStream().map(Object::toString).toArray(String[]::new);
	}
	public Stream<T> stream() {
		return StreamUtils.createStream(new InnerIterator());
	}

	public Stream<T> markStream() {
		return this.markIndexStream().mapToObj(this::get);
	}

	public Stream<T> unMarkStream() {
		return this.unMarkIndexStream().mapToObj(this::get);
	}

	@Override
	public Iterator<T> iterator() {
		return this.unMarkStream().iterator();
	}

	public List<T> getFragmentStartAt(int startIndexInclusive) {
		int endIndexInclusive = this.getFragmentEndIndexStartAt(startIndexInclusive);
		return endIndexInclusive < minIndex() ? Collections.emptyList() :
			IntStream.rangeClosed(startIndexInclusive, endIndexInclusive)
				.mapToObj(this::get).collect(Collectors.toList());
	}

	public List<T> getFragmenEndAt(int endIndexInclusive) {
		int startIndexInclusive = this.getFragmenStartIndextEndAt(endIndexInclusive);
		return startIndexInclusive < minIndex() ? Collections.emptyList() : 
			IntStream.range(startIndexInclusive, endIndexInclusive).mapToObj(this::get).collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return String.format("%s [strings=%s, marks=%s, minIndex=%s, maxIndex=%s, remains=%s]",
				this.getClass().getSimpleName(), Arrays.toString(datas), Arrays.toString(this.datas), this.minIndex(), this.maxIndex(), Arrays.toString(this.unMarkString()));
	}

	protected abstract Mash createMash(byte config);
	
	protected T[] datas;
	
	/***
	 * Init with word array, mash
	 * @param datas Input data
	 */
	protected final void init(T[] datas, byte config) {
		this.datas = datas;
		this.createMash(config);
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
	}
}
