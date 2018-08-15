package com.bkav.command.common.active.result;

import com.bkav.command.common.CommandRequestModel;
import com.bkav.command.common.ResultCommandValue;

public abstract class ResultCommandValueAbstract implements ResultCommandValue {

	public ResultCommandValueAbstract(CommandRequestModel commandRequest) {
		this.commandRequest = commandRequest;
	}

	@Override
	public CommandRequestModel getCommandRequestModel() {
		return this.commandRequest;
	}

	private CommandRequestModel commandRequest;
}
