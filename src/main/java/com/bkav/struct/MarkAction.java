package com.bkav.struct;

public interface MarkAction<T extends Object> {
	
	public T mark(T object);
	public T unmark(T object);
	public boolean isMark(T object);
	public boolean isUnMark(T object);
}
