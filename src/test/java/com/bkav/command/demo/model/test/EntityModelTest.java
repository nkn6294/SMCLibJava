package com.bkav.command.demo.model.test;

import org.junit.Before;
import org.junit.Test;

import com.bkav.command.SMCManager;
import com.bkav.command.common.Model;
import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.HomeTest;
import com.bkav.command.demo.model.HomeEntityModel;
import com.bkav.command.model.ModelTest;
import com.bkav.command.struct.ResultsProcess;
import com.bkav.command.util.CollectionUtil;
import com.bkav.command.util.StringUtil;

public class EntityModelTest extends ModelTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commands = CollectionUtil.convert(SampleData.SampleCommands);
	}
	
	@Override
	protected Model createModel() {
		return new HomeEntityModel(HomeTest.getHomeTest()); 
	}
	
	@Test
	public void testCommand() {
		String[] command = new String[] {"tang", "dieu", "hoa", "phong", "ngu"}; 
		command = new String[] {"dieu", "chinh", "dieu", "hoa", "1", "nho", "nhat"};
		ResultsProcess result = new ResultsProcess(command);
		result = this.model.process(result);
		String commandString = StringUtil.joinString(command);
		SMCManager.logger.info("<" + commandString + ">");
		SMCManager.logger.info(result.toString());
	}
}
