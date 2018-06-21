package com.bkav.command.demo.model.control;

import com.bkav.command.model.CollectionModel;

public class SimpleControlModel extends CollectionModel {

	@Override
	protected void init() {
		super.init();
		this.modelName = "SIMPLE_CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(new MinControlModel(), 
				new MaxControlModel());
	}
	
}