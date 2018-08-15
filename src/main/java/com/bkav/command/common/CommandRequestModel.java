package com.bkav.command.common;

import com.bkav.command.common.Consumer;

public interface CommandRequestModel {
	public void process(Consumer<ResultCommandValue> completedHandler);
	public void setCommandString(String commandString);
	public String getCommandString();
}