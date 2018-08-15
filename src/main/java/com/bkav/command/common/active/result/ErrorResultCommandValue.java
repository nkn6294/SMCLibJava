package com.bkav.command.common.active.result;

import com.bkav.command.common.CommandRequestModel;

public class ErrorResultCommandValue extends BooleanResultCommandValue {

	public ErrorResultCommandValue(CommandRequestModel commandRequest, String message) {
		super(commandRequest, false, message);
	}
}
