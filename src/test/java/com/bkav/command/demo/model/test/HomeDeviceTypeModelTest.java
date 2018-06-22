package com.bkav.command.demo.model.test;

import com.bkav.command.common.Model;
import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.model.HomeDeviceTypeModel;

public class HomeDeviceTypeModelTest extends EntityModelTest {

	@Override
	protected Model createModel() {
		return new HomeDeviceTypeModel(SampleData.DEVICE_TYPE);
	}
}
