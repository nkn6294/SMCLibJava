package com.bkav.command.common.active;

import com.bkav.command.common.active.result.BooleanResultCommandValue;
import com.bkav.command.common.active.result.ErrorResultCommandValue;

public class NonCommandActive extends CommandActiveSynchorizedCommand {

	public NonCommandActive() {
		super();
		this.formatMessage = "Lệnh không hợp lệ:%1$s";
	}
	public NonCommandActive(String formatMessage) {
		super();
		this.formatMessage = formatMessage;
	}
	
	@Override
	public BooleanResultCommandValue runCommandWithTypeResult() {
		String message = String.format(this.formatMessage, this.getCommandString());
		return new ErrorResultCommandValue(this, message);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [commandString=" + commandString + "]";
	}

	protected String formatMessage;
}