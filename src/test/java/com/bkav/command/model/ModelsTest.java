package com.bkav.command.model;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.bkav.command.SystemManager.*;

import com.bkav.command.SystemManager;
import com.bkav.command.data.SampleData;
import com.bkav.command.util.CollectionUtil;
import com.bkav.struct.ResultsProcess;

public abstract class ModelsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.pipeLineModel = this.createModels();
		this.commands = this.createCommands();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testModels() {
		for (String[] command : commands) {
			String commandString = String.join(" ", command);
			logger.info("<" + commandString + ">");
			pipeLineModel.stream().filter(item -> item instanceof CollectionModel)
			.forEach(item -> {
				((CollectionModel) item).test(command);
			});
		}
		assertTrue(true);// TODO test modelsTest.
	}
	
	@Test
	public final void testProcessModels() {
		for (String[] command : commands) {
			String commandString = String.join(" ", command);
			logger.info("<" + commandString + ">");
			ResultsProcess result = new ResultsProcess(command);
			result = pipeLineModel.process(result);
			logger.info(result.toString());
		}
		assertTrue(true);// TODO test modelsTest.
	}
	
	protected PipeLineModel pipeLineModel;
	protected String[][] commands;

	protected abstract PipeLineModel createModels();
	protected String[][] createCommands() {
		return CollectionUtil.convert(this.getTestCommands(), SystemManager.textProcesser);
	}
	protected String[] getTestCommands() {
		return SampleData.SampleCommands;
	}
}
