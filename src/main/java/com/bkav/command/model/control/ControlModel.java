package com.bkav.command.model.control;

import com.bkav.command.model.CollectionModel;

public class ControlModel extends CollectionModel { 

	@Override
	protected void init() {
		super.init();
		this.MODEL_NAME = "CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new SimpleControlModel(), 
				new AdvanceControlModel());
	}

}
