package com.bkav.command.struct.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ResultsFind<T> implements Iterable<T> {

	@SafeVarargs
	public ResultsFind(String[] remains, T... values) {
		this.values = new ArrayList<>();
		for (int index = 0; index < values.length; index++) {
			this.values.add(values[index]);
		}
		this.remains = remains;
	}

	public ResultsFind(String[] remains, Collection<T> values) {
		this.values = new ArrayList<>();
		Iterator<T> iterator = values.iterator();
		while (iterator.hasNext()) {
			this.values.add(iterator.next());
		}
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
