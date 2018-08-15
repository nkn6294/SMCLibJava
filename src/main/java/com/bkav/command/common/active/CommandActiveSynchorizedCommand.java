package com.bkav.command.common.active;

import com.bkav.command.common.CommandActiveAble;
import com.bkav.command.common.ResultCommandValue;
import com.bkav.command.common.active.result.BooleanResultCommandValue;

public abstract class CommandActiveSynchorizedCommand extends CompletedHandlerSynchorizedCommand
		implements CommandActiveAble {

	@Override
	public abstract BooleanResultCommandValue runCommandWithTypeResult();

	@Override
	public ResultCommandValue runCommand() {
		return this.runCommandWithTypeResult();
	}

}
