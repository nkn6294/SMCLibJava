package com.bkav.command.model.entity;

import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.ModelData;
import com.bkav.home.data.HomeDeviceType;

public class HomeDeviceTypeModel extends StaticInputWordsModel<HomeDeviceType> {

	public HomeDeviceTypeModel() {
		super(ModelData.DEVICE_TYPE);
		this.modelName = "DEVICE_TYPE";
	}

	@Override
	protected HomeDeviceType getDataFromStringArray(String[] datas) {
		return HomeDeviceType.createFromStringArray(datas);
	}
}
