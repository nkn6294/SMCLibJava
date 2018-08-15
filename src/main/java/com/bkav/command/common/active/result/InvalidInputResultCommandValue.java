package com.bkav.command.common.active.result;

import com.bkav.command.common.CommandRequestModel;

public class InvalidInputResultCommandValue extends ErrorResultCommandValue {

	public InvalidInputResultCommandValue(CommandRequestModel commandRequest) {
		super(commandRequest, String.format("Lệnh %s không chính xác", commandRequest != null ? commandRequest.getCommandString() : ""));
	}
	
}
