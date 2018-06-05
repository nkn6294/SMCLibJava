package com.bkav.command.model.entity;

import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;

public class HomeDeviceModelTest extends EntityModelTest {

	@Override
	protected Model createModel() {
		return new HomeDeviceModel(SampleData.DEVICES);
	}
}
