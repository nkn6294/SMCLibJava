package com.bkav.command.common.active.result;

import com.bkav.command.common.CommandRequestModel;

public class BooleanResultCommandValue extends ResultCommandValueAbstract {

	public BooleanResultCommandValue(CommandRequestModel commandRequest, boolean result, String message) {
		super(commandRequest);
		this.result = result;
		this.message = message;
	}

	public BooleanResultCommandValue(CommandRequestModel commandRequest, boolean result) {
		this(commandRequest, result, null);
	}

	@Override
	public boolean isSuccess() {
		return this.result;
	}

	@Override
	public int getValue() {
		return this.result ? 100 : -1;
	}

	@Override
	public String getMessage() {
		return this.message != null ? message : this.isSuccess() + "";
	}

	private boolean result;
	private String message;

}
