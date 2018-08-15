package com.bkav.command.model.common;

import com.bkav.command.common.Model;
import com.bkav.command.struct.result.ResultsProcess;

/***
 * Supplier model with before resultsProcess
 */
public interface ModelSupplierWithResultProcess {
	public enum Mode {
		AUTO_RESET,
		MANUAL_RESET
	}
	public class Config {
		
		public void mode(Mode mode) {
			this.mode = mode;
		}
		public Mode mode() {
			return this.mode;
		}
		private Mode mode;
	}
	/***
	 * Get next {@link Model} with {@link ResultsProcess}
	 * @param resultsProcess result process before save in instance of {@link ResultsProcess}
	 * @return next {@link Model} with {@link ResultsProcess} or null
	 */
	public Model get(ResultsProcess resultsProcess);
	
	public void reset();
}
