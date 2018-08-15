package com.bkav.command.common;

public interface ResultCommandValue {
	public boolean isSuccess();
	public int getValue();
	public String getMessage();
	public CommandRequestModel getCommandRequestModel();
}
