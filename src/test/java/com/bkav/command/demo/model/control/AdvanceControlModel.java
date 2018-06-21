package com.bkav.command.demo.model.control;

import com.bkav.command.model.CollectionModel;

public class AdvanceControlModel extends CollectionModel {

	@Override
	protected void init() {
		super.init();
		this.modelName = "ADVANCE_CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(new LowerControlModel(), 
				new UpperControlModel(), 
				new ValueControlModel());
	}
}
