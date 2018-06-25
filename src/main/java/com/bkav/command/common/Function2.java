package com.bkav.command.common;

public interface Function2<T1, T2, R> {
	public R apply(T1 value1, T2 value2);
}
