package com.bkav.command.struct;

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

import com.bkav.command.util.StreamUtils;

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
				temp.forEach(item::add);
				result.add(item);
				temp.clear();
			}
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
				temp.forEach(item::add);
				result.add(item);
				temp.clear();
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
//		return new InnerIterator();
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
		return String.format("%s [datas=%s, mash=%s, remains=%s]", this.getClass().getSimpleName(), Arrays.toString(datas), mash.toString(), Arrays.toString(this.unMarkString()));
	}

	protected T[] datas;
	
	/***
	 * Init with word array, mash
	 * @param datas Input data
	 */
	protected final void init(T[] datas, MaskConfig config) {
		this.datas = datas;
		this.createMash(datas.length, config);
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
