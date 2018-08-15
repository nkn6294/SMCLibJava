package com.bkav.command.model.time;

import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;
import com.bkav.command.model.ModelTest;

public class ShortDateModelTest extends ModelTest {

	@Override
	protected Model createModel() {
		return new ShortDateModel();
	}

	@Override
	protected String[] getTestCommands() {
		return SampleData.SampleDateSchedule;
	}
}
