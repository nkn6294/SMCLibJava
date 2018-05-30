package com.bkav.command.model.entity;

import com.bkav.command.model.CollectionModel;

public class HomeEntityModel extends CollectionModel {

	@Override
	protected void init() {
		super.init();
		this.modelName = "ENTITY";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new HomeDeviceModel(),
				new HomeFunctionModel(), 
				new HomeAreaModel(),
				new HomeDeviceTypeModel());
	}
}
