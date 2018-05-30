package com.bkav.struct;

import java.util.Arrays;

public abstract class CommonMaskWithMarkAction<T> extends CommonMaskWithType<T> {

	public CommonMaskWithMarkAction(int length, MarkAction<T> markAction) {
		super(length);
		this.markAction = markAction;
	}
	public CommonMaskWithMarkAction(int length, MaskConfig config, MarkAction<T> markAction) {
		super(length, config);
		this.markAction = markAction;
	}

	@Override
	public final boolean isMark(int index) {
		T value = this.getMarkValueAt(index);
		return this.markAction.isMark(value);
	}
	@Override
	public final boolean isUnMark(int index) {
		T value = this.getMarkValueAt(index);
		return this.markAction.isUnMark(value);
	}
	
	@Override
	public String toString() {
		return String.format("%s [markAction=%s, marks=%s, minIndex=%s, maxIndex=%s, config=%s]", this.getClass().getSimpleName(),
				this.markAction, Arrays.toString(this.masks), this.minIndex, this.maxIndex, this.config);
	}

	protected MarkAction<T> markAction;

	@Override
	protected final void resetMark(int index) {
		T currentValue = this.getMarkValueAt(index);
		this.setMarkValueAt(index, this.markAction.unmark(currentValue));
	}
	@Override
	protected final void markByIndex(int index) {
		T currentValue = this.getMarkValueAt(index);
		this.setMarkValueAt(index, this.markAction.mark(currentValue));
	}
}
