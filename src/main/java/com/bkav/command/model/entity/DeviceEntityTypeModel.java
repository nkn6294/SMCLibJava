package com.bkav.command.model.entity;

import com.bkav.command.data.HomeEntityType;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class DeviceEntityTypeModel extends StaticInputWordsModel<HomeEntityType> {

	public DeviceEntityTypeModel() {
		super(ModelData.DEVICE_ENTITY_TYPE);
		this.modelName = "DEVICE_ENTITY_TYPE";		
	}

	@Override
	protected HomeEntityType getDataFromStringArray(String[] datas) {
		return HomeEntityType.createFromStringArray(datas, EntityType.DEVICE);
	}

}
