package com.bkav.command.model.entity;

import com.bkav.command.common.Model;
import com.bkav.command.model.ModelData;

public class HomeDeviceTypeModelTest extends EntityModelTest {

	@Override
	protected Model createModel() {
		return new HomeDeviceTypeModel(ModelData.DEVICE_TYPE);
	}
}
