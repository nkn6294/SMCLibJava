package com.bkav.command.struct;

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
		for (int index = 0; index < length; index++) {
			masks[index] = 0;
		}
		return masks;
	}
}
