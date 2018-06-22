package com.bkav.command.struct;

import java.security.InvalidParameterException;

public abstract class AbstractMarkAction<T> implements MarkAction<T> {

	public AbstractMarkAction(MaskConfig config) {
		this.config = config;
	}

	public AbstractMarkAction() {
		this.config = MaskConfig.getDefaultConfig();
	}

	protected MaskConfig config;

	protected boolean checkInput(T value) {
		if (this.isValidInput(value)) {
			return true;
		}
		if (this.config.isStrictMode()) {
			throw new InvalidParameterException();
		}
		return false;
	}

	protected boolean isValidInput(T value) {
		return value != null;
	}
}
