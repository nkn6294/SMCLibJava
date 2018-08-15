package com.bkav.command.common.active.result;

import com.bkav.command.common.CommandRequestModel;

public class SuccessResultCommandValue extends BooleanResultCommandValue {

	public SuccessResultCommandValue(CommandRequestModel commandRequest, String message) {
		super(commandRequest, true, message);
	}
}
