package com.bkav.struct;

public interface MarkAble<T> {
	public T mark(T value);
	public T unmark(T value);
	public boolean isMark(T value);
	public boolean isUnmark(T value);
}
