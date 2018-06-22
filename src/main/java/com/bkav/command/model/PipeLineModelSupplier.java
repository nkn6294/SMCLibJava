package com.bkav.command.model;


import java.util.Iterator;

import com.bkav.command.common.Model;
import com.bkav.command.struct.ResultsProcess;

public class PipeLineModelSupplier implements ModelSupplierWithResultProcess {

	public PipeLineModelSupplier(PipeLineModel pipeLine) {
		if (pipeLine == null) {
			throw new NullPointerException();
		}
		this.pipeLineModel = pipeLine;
		this.iterator = this.pipeLineModel.iterator();
	}
	
	@Override
	public Model get(ResultsProcess resultsProcess) {
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}
	
	protected PipeLineModel pipeLineModel;
	protected Iterator<Model> iterator;
}
