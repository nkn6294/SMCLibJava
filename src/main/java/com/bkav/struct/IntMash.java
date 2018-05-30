package com.bkav.struct;

public class IntMash extends CommonMashWithMarkAction<Integer> {

	public IntMash(int length) {
		super(length, new IntMarkAction());
	}

	public IntMash(int length, byte config) {
		super(length, config, new IntMarkAction());
	}

	@Override
	protected Integer[] createMarkMarkArrayValue(int length) {
		return new Integer[length];
	}
}
