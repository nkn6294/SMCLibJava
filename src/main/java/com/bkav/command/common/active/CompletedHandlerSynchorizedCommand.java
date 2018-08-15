package com.bkav.command.common.active;

import com.bkav.command.common.Consumer;
import com.bkav.command.common.ResultCommandValue;

public abstract class CompletedHandlerSynchorizedCommand extends CommandRequestModelAbstract {
	public void runCompletedHandler(final ResultCommandValue result, final Consumer<ResultCommandValue> completedHandler) {
		//TODO: Run other thread.
		completedHandler.accept(result);
//		SMCService.homeCommandThread.runOnOtherProcessThread(
//				new Handler() {
//					
//					@Override
//					public void apply() {
//						completedHandler.accept(result);
//					}
//				});
				
	}

}