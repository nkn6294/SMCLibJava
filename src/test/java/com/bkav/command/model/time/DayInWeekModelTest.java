package com.bkav.command.model.time;

import com.bkav.command.model.CommonModel;

public class DayInWeekModelTest extends TimeModelTest {

	@Override
	protected CommonModel<?> createModel() {
		return new DayInWeekModel();
	}
}
