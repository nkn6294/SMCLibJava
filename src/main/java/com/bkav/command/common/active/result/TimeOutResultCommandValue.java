package com.bkav.command.common.active.result;

import com.bkav.command.common.CommandRequestModel;

public class TimeOutResultCommandValue extends ErrorResultCommandValue {

	public TimeOutResultCommandValue(CommandRequestModel commandRequest) {
		super(commandRequest, String.format("Hết thời gian xử lý lệnh \\`%s\\`", commandRequest != null ? commandRequest.getCommandString() : ""));
	}
}
