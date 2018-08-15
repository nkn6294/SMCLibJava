package com.bkav.command.common.active.result;

import com.bkav.command.common.CommandRequestModel;

public class IntegerResultCommandValue extends ResultCommandValueAbstract {

	public IntegerResultCommandValue(CommandRequestModel commandRequest, int result, String message) {
		super(commandRequest);
		this.result = result;
		this.message = message;
	}

	public IntegerResultCommandValue(CommandRequestModel commandRequest, int result) {
		this(commandRequest, result, null);
	}

	@Override
	public boolean isSuccess() {
		return this.result > 0;
	}

	@Override
	public int getValue() {
		return this.result;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	private int result;
	private String message;

}
