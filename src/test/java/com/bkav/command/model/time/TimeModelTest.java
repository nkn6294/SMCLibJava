package com.bkav.command.model.time;

import com.bkav.command.common.Model;
import com.bkav.command.demo.SampleData;
import com.bkav.command.model.ModelTest;

public class TimeModelTest extends ModelTest {

	@Override
	protected Model createModel() {
		return new TimeModel();
	}
	protected String[] getTestCommands() {
		return SampleData.SampleSchedule;
	}
}
