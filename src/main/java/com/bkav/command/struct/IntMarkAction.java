package com.bkav.command.struct;

public class IntMarkAction extends AbstractMarkAction<Integer> {

	@Override
	public Integer mark(Integer object) {
		if (!this.checkInput(object)) {
			return Integer.valueOf(1);
		}
		if (object.intValue() > 0) {
			return Integer.valueOf(object + 1);
		}
		return Integer.valueOf(1);
	}

	@Override
	public Integer unmark(Integer object) {
		if (!this.checkInput(object)) {
			return Integer.valueOf(0);
		}
		return Integer.valueOf(0);
	}

	@Override
	public boolean isMark(Integer object) {
		if (!this.checkInput(object)) {
			return false;
		}
		return object.intValue() > 0;
	}

	@Override
	public boolean isUnMark(Integer object) {
		if (!this.checkInput(object)) {
			return false;
		}
		return object.intValue() <= 0;
	}

	@Override
	public String toString() {
		return String.format("%s", this.getClass().getSimpleName());
	}

}
