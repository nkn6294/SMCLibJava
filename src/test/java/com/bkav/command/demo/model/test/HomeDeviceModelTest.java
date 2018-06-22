package com.bkav.command.demo.model.test;

import com.bkav.command.common.Model;
import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.model.HomeDeviceModel;

public class HomeDeviceModelTest extends EntityModelTest {

	@Override
	protected Model createModel() {
		return new HomeDeviceModel(SampleData.DEVICES);
	}
}
