package com.bkav.command.demo.model.test;

import org.junit.Before;

import com.bkav.command.common.Model;
import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.model.control.ControlModel;
import com.bkav.command.model.ModelTest;
import com.bkav.command.util.CollectionUtil;

public class ControlModelTest extends ModelTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commands = CollectionUtil.convert(SampleData.SampleCommands);
	}
	
	@Override
	protected Model createModel() {
		return new ControlModel(); 
	}

}
