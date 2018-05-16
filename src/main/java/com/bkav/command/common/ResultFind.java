package com.bkav.command.common;

import java.util.Arrays;

public class ResultFind<T> {
	public T value;
	public String[] remains;

	public ResultFind(T value, String[] remain) {
		this.value = value;
		this.remains = remain;
	}

	@Override
	public String toString() {
		return String.format("ResultFind [value=%s, remain=%s]", value, Arrays.toString(remains));
	}
}
