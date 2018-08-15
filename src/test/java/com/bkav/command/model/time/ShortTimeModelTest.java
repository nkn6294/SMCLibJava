package com.bkav.command.model.time;

import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;
import com.bkav.command.model.ModelTest;

public class ShortTimeModelTest extends ModelTest {

	@Override
	protected Model createModel() {
		return new ShortTimeModel();
	}

	@Override
	protected String[] getTestCommands() {
		return SampleData.SampleTimeSchedule;
	}
}
