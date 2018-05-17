package com.bkav.command.model.control;

import com.bkav.command.model.CommonModel;

public class MaxControlModelTest extends ControlModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new MaxControlModel();
	}
}
