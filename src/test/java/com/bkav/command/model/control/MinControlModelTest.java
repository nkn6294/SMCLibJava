package com.bkav.command.model.control;

import com.bkav.command.model.CommonModel;

public class MinControlModelTest extends ControlModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new MinControlModel();
	}
}
