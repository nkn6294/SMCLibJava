package com.bkav.command.demo.model;

import com.bkav.command.model.CollectionModel;

public class HomeEntityTypeModel extends CollectionModel {

	@Override
	protected void init() {
		super.init();
		this.modelName = "ENTITY_TYPE";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new DeviceEntityTypeModel(),
				new FunctionEntityTypeModel());
	}
}
