package com.bkav.command.model;

import com.bkav.command.common.Model;
import com.bkav.command.struct.ResultsProcess;

/***
 * Supplier model with before resultsProcess
 */
public interface ModelSupplierWithResultProcess {
	/***
	 * Get next {@link Model} with {@link ResultsProcess}
	 * @param resultsProcess result process before save in instance of {@link ResultsProcess}
	 * @return next {@link Model} with {@link ResultsProcess} or null
	 */
	public Model get(ResultsProcess resultsProcess);
}
