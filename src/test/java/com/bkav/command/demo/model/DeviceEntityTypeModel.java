package com.bkav.command.demo.model;

import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.HomeEntityType;
import com.bkav.command.model.StaticInputWordsModel;

public class DeviceEntityTypeModel extends StaticInputWordsModel<HomeEntityType> {

	public DeviceEntityTypeModel() {
		super(SampleData.DEVICE_ENTITY_TYPE);
		this.modelName = "DEVICE_ENTITY_TYPE";		
	}

	@Override
	protected HomeEntityType getDataFromStringArray(String[] datas) {
		return HomeEntityType.createFromStringArray(datas, EntityType.DEVICE);
	}

}
