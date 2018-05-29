package com.bkav.command.model.entity;

import org.junit.Before;
import org.junit.Test;

import com.bkav.command.SystemManager;
import com.bkav.command.common.Model;
import com.bkav.command.model.ModelTest;
import com.bkav.command.test.SampleData;
import com.bkav.struct.ResultsProcess;
import com.bkav.util.CollectionUtil;

public class EntityModelTest extends ModelTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commands = CollectionUtil.convert(SampleData.SampleCommands2);
	}
	
	@Override
	protected Model createModel() {
		return new HomeEntityModel(); 
	}
	
	@Test
	public void testCommand() {
		String[] command = new String[] {"tang", "dieu", "hoa", "phong", "ngu"}; 
		command = new String[] {"dieu", "chinh", "dieu", "hoa", "1", "nho", "nhat"};
		ResultsProcess result = new ResultsProcess(command);
		result = this.model.process(result);
		String commandString = String.join(" ", command);
		SystemManager.logger.info("<" + commandString + ">");
		SystemManager.logger.info(result.toString());
	}
}
