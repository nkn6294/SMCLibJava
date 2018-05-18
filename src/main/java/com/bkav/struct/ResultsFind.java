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

	public String[] getRemains() {
		return remains;
	}

	public Stream<T> stream() {
		return values.stream();
	}

	@Override
	public Iterator<T> iterator() {
		return this.values.iterator();
	}

	private List<T> values;
	private String[] remains;
}
