package com.bkav.struct;

import java.security.InvalidParameterException;

public abstract class AbstractMarkAction<T> implements MarkAction<T> {

	public AbstractMarkAction(byte config) {
		this.config = config;
	}

	public AbstractMarkAction() {
		this.config = NORMAL_MODE;
	}

	protected byte config = NORMAL_MODE;

	protected boolean checkInput(T value) {
		if (this.isValidInput(value)) {
			return true;
		}
		boolean strictMode = (this.config & STRICT_MODE) > 0;
		if (strictMode) {
			throw new InvalidParameterException();
		}
		return false;
	}

	public boolean isValidInput(T value) {
		return value != null;
	}
}
