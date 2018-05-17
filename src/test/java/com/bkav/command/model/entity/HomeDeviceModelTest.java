package com.bkav.command.model.entity;

import com.bkav.command.model.CommonModel;

public class HomeDeviceModelTest extends EntityModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new HomeDeviceModel();
	}
}
