package com.bkav.command.common;

public interface Function<T, R> {
	public R apply(T value);
}

