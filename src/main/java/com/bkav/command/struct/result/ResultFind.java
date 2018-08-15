package com.bkav.command.struct.result;

import java.util.Arrays;

public class ResultFind<T> {

	public ResultFind(T value, String[] detects, String[] remain) {
		this.value = value;
		this.remains = remain;
		this.detects = detects;
	}

	public T getValue() {
		return value;
	}

	public String[] getRemains() {
		return remains;
	}
	
	public String[] getDetects() {
		return this.detects;
	}

	@Override
	public String toString() {
		return String.format("%s [value=%s, remain=%s]", this.getClass().getSimpleName(), value, Arrays.toString(remains));
	}

	private T value;
	private String[] remains;
	private String[] detects;
}
