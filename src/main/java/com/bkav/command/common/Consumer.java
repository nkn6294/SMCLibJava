package com.bkav.command.common;

public interface Consumer<T> {
	public void accept(T t);
}
