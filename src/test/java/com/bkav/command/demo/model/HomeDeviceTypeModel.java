package com.bkav.command.demo.model;

import com.bkav.command.demo.data.HomeDeviceType;
import com.bkav.command.model.StaticInputWordsModel;

public class HomeDeviceTypeModel extends StaticInputWordsModel<HomeDeviceType> {

	public HomeDeviceTypeModel(String[] datas) {
		super(datas);
		this.modelName = "DEVICE_TYPE";
	}

	@Override
	protected HomeDeviceType getDataFromStringArray(String[] datas) {
		return HomeDeviceType.createFromStringArray(datas);
	}
}
