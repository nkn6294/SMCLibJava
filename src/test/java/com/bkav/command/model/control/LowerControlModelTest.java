package com.bkav.command.model.control;

import com.bkav.command.model.CommonModel;

public class LowerControlModelTest extends ControlModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new LowerControlModel();
	}
}
