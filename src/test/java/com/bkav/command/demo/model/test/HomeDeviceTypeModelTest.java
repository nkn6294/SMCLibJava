package com.bkav.command.demo.model.test;

import com.bkav.command.common.Model;
import com.bkav.command.demo.model.HomeDeviceTypeModel;
import com.bkav.command.model.ModelData;

public class HomeDeviceTypeModelTest extends EntityModelTest {

	@Override
	protected Model createModel() {
		return new HomeDeviceTypeModel(ModelData.DEVICE_TYPE);
	}
}
