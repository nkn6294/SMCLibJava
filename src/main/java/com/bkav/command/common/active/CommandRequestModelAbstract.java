package com.bkav.command.common.active;

import com.bkav.command.common.CommandActiveAble;
import com.bkav.command.common.Consumer;
import com.bkav.command.common.ResultCommandValue;

public abstract class CommandRequestModelAbstract implements CommandActiveAble {
	@Override
	public void setCommandString(String commandString) {
		this.commandString = commandString;
	}

	@Override
	public String getCommandString() {
		return this.commandString;
	}

	@Override
	public void process(Consumer<ResultCommandValue> completedHandler) {
		ResultCommandValue result = this.runCommand();
		if (result != null && completedHandler != null) {
			this.runCompletedHandler(result, completedHandler);
		}
	}

	protected String commandString;

	public abstract ResultCommandValue runCommand();

	public abstract void runCompletedHandler(ResultCommandValue result, Consumer<ResultCommandValue> completedHandler);
}
