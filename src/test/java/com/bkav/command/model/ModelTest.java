package com.bkav.command.model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.SystemManager;
import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;
import com.bkav.command.util.CollectionUtil;
import com.bkav.struct.ResultsProcess;

public abstract class ModelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.model = this.createModel();
		this.commands = this.createCommands();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testModel() {
		if (this.model == null || this.commands == null) {
			return;
		}
		Arrays.stream(this.commands).forEach(command -> {
			SystemManager.logger.info("-------------------------");
			String commandString = String.join(" ", command);
			SystemManager.logger.info("<" + commandString + ">");
			ResultsProcess result = new ResultsProcess(command);
			result = this.model.process(result);
//			this.model.test(command);
			SystemManager.logger.info(result.toString());
		});
		assertTrue(true); // TODO TestModel model
	}

	protected Model model;
	protected String[][] commands;

	protected abstract Model createModel();
	protected String[][] createCommands() {
		return CollectionUtil.convert(this.getTestCommands(), SystemManager.textProcesser);
	}
	protected String[] getTestCommands() {
		return SampleData.SampleCommands;
	}
}
