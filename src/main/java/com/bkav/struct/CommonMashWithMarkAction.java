package com.bkav.struct;

public abstract class CommonMashWithMarkAction<T> extends CommonMashWithType<T> {

	public CommonMashWithMarkAction(int length, MarkAction<T> markAction) {
		super(length);
		this.markAction = markAction;
	}
	public CommonMashWithMarkAction(int length, byte mode, MarkAction<T> markAction) {
		super(length, mode);
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
