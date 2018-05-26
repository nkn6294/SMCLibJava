package com.bkav.command.model.control;

import com.bkav.command.common.Model;

public class MinControlModelTest extends ControlModelTest {

	@Override
	protected Model createModel() {
		return new MinControlModel();
	}
}
