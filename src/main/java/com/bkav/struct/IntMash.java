package com.bkav.struct;

public class IntMash extends CommonMash {

	public IntMash(int length) {
		super(length);
	}

	public IntMash(int length, byte config) {
		super(length, config);
	}

	@Override
	protected void createMarkArray(int length) {
		this.marks = new int[length];
	}

	@Override
	protected void resetMark(int index) {
		this.marks[index] = 0;
	}

	@Override
	protected void markByIndex(int index) {
		if (this.marks[index] >= 0) {
			this.marks[index] += 1;
		} else {
			this.marks[index] = 1;
		}
	}

	@Override
	protected boolean isMarkByIndex(int index) {
		return this.marks[index] > 0;
	}

	@Override
	protected boolean isUnmarkByIndex(int index) {
		return this.marks[index] <= 0;
	}
	
	
	private int[] marks;
}
