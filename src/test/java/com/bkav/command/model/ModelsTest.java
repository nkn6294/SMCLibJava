package com.bkav.command.model;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.SystemManager;
import com.bkav.command.data.HomeTest;
import com.bkav.command.data.SampleData;
import com.bkav.command.model.control.ControlModel;
import com.bkav.command.model.entity.HomeEntityModel;
import com.bkav.command.model.misc.AmountModel;
import com.bkav.command.model.time.TimeModel;
import com.bkav.struct.ResultsProcess;
import com.bkav.util.CollectionUtil;

public class ModelsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.pipeLineModel = new PipeLineModel(
				new HomeEntityModel(HomeTest.getHomeTest()), 
				new AmountModel(),
				new ControlModel(), 
				new TimeModel());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testModels() {
		for (String[] command : commands) {
			SystemManager.logger.info("-------------------------");
			String commandString = String.join(" ", command);
			SystemManager.logger.info("<" + commandString + ">");
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
			SystemManager.logger.info("-------------------------");
			String commandString = String.join(" ", command);
			SystemManager.logger.info("<" + commandString + ">");
			ResultsProcess result = new ResultsProcess(command);
			result = pipeLineModel.process(result);
			SystemManager.logger.info(result.toString());
		}
		assertTrue(true);// TODO test modelsTest.
	}
	
	protected PipeLineModel pipeLineModel;
	protected String[][] commands = CollectionUtil.convert(SampleData.SampleCommands2);
}
