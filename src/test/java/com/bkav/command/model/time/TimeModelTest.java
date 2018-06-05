package com.bkav.command.model.time;

import org.junit.Before;

import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;
import com.bkav.command.model.ModelTest;
import com.bkav.util.CollectionUtil;

public class TimeModelTest extends ModelTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commands = CollectionUtil.convert(SampleData.SampleSchedule);
	}
	@Override
	protected Model createModel() {
		return new TimeModel();
	}
}
