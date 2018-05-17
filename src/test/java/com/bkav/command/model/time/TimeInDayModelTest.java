package com.bkav.command.model.time;

import com.bkav.command.model.CommonModel;

public class TimeInDayModelTest extends TimeModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new TimeInDayModel();
	}
}
