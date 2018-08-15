package com.bkav.command.common;

public interface CommandRequestModelWithType<T extends ResultCommandValue> extends CommandRequestModel {
	public T runCommandWithTypeResult();
}
