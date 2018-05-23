package com.bkav.command.model.entity;

import com.bkav.command.data.CommonData;
import com.bkav.command.model.CollectionModel;

public class HomeEntityModel extends CollectionModel<CommonData> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "ENTITY";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new HomeFunctionModel(), 
				new HomeDeviceModel(),
				new HomeAreaModel(),
				new HomeDeviceTypeModel());
	}

	@Override
	protected CommonData getDataFromStringArray(String[] datas) {
		String id = CommonData.getSimpleName(datas);
		return new CommonData(id);
	}
}
