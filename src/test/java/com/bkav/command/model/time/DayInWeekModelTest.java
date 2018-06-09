package com.bkav.command.model.time;

import com.bkav.command.common.Model;

public class DayInWeekModelTest extends TimeModelTest {

	@Override
	protected Model createModel() {
		return new DayOfWeekModel();
	}
}
