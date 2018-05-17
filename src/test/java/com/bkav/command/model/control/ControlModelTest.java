package com.bkav.command.model.control;

import org.junit.Before;

import com.bkav.command.model.CommonModel;
import com.bkav.command.model.ModelTest;
import com.bkav.command.test.SampleData;
import com.bkav.util.CollectionUtil;

public class ControlModelTest extends ModelTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commands = CollectionUtil.convert(SampleData.SampleCommands);
	}
	
	@Override
	protected CommonModel<?> createModel() {
		return new ControlModel(); 
	}

}
