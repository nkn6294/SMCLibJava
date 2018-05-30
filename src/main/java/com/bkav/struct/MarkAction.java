package com.bkav.struct;

public interface MarkAction<T extends Object> {
	public static final byte NORMAL_MODE = 1 << 0;
	public static final byte STRICT_MODE = 1 << 1;
	
	public T mark(T object);
	public T unmark(T object);
	public boolean isMark(T object);
	public boolean isUnMark(T object);
}
