package com.bkav.struct;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultsFind<T> implements Iterable<T> {

	@SafeVarargs
	public ResultsFind(String[] remains, T... values) {
		this.values = Arrays.stream(values).collect(Collectors.toList());
		this.remains = remains;
	}

	public ResultsFind(String[] remains, Collection<T> values) {
		this.values = values.stream().collect(Collectors.toList());
		this.remains = remains;
	}
	
	public int size() {
		return this.values.size();
	}
	
	public boolean isEmpty() {
		return this.values.isEmpty();
	}
	
	public void addValue(T value) {
		this.values.add(value);
	}

	public void removeValue(T value) {
		this.values.remove(value);
	}

	public void clear() {
		this.values.clear();
	}

	public String[] remains() {
		return this.remains;
	}
	
	public void setRemains(String[] remains) {
		this.remains = remains;
	}

	public Stream<T> stream() {
		return this.values.stream();
	}

	@Override
	public Iterator<T> iterator() {
		return this.values.iterator();
	}

	@Override
	public String toString() {
		return String.format("%s [values=%s, remains=%s]", this.getClass().getSimpleName(), values, Arrays.toString(remains));
	}

	private List<T> values;
	private String[] remains;
}
