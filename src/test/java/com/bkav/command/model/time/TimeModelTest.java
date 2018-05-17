package com.bkav.command.model.time;

import org.junit.Before;

import com.bkav.command.model.CommonModel;
import com.bkav.command.model.ModelTest;
import com.bkav.command.test.SampleData;
import com.bkav.util.CollectionUtil;

public class TimeModelTest extends ModelTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commands = CollectionUtil.convert(SampleData.SampleSchedule);
	}
	@Override
	protected CommonModel<?> createModel() {
		return new TimeModel();
	}
}
