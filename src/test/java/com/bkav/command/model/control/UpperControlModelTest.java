package com.bkav.command.model.control;

import com.bkav.command.model.CommonModel;

public class UpperControlModelTest extends ControlModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new UpperControlModel();
	}
}
