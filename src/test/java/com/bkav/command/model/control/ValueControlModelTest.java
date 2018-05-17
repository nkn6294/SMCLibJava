package com.bkav.command.model.control;

import com.bkav.command.model.CommonModel;

public class ValueControlModelTest extends ControlModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new ValueControlModel();
	}
}
