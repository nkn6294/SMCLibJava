package com.bkav.struct;

import java.util.stream.IntStream;

public class IntMask extends CommonMaskWithMarkAction<Integer> {

	public IntMask(int length) {
		super(length, new IntMarkAction());
	}

	public IntMask(int length, MaskConfig config) {
		super(length, config, new IntMarkAction());
	}

	@Override
	protected Integer[] createMaskArrayValue(int length) {
		Integer[] masks = new Integer[length];
		IntStream.range(0, length).forEach(index -> masks[index] = 0);
		return masks;
	}
}
