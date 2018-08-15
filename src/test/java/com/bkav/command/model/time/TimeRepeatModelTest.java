package com.bkav.command.model.time;

import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;

public class TimeRepeatModelTest extends TimeModelTest {

	@Override
	protected Model createModel() {
		return new TimeRepeatModel();
	}
	
	@Override
	protected String[] getTestCommands() {
		return SampleData.SampleTimeRepeat;
	}
}
