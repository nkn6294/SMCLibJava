package com.bkav.command.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.SystemManager;
import com.bkav.command.model.control.ControlModel;
import com.bkav.command.model.entity.HomeEntityModel;
import com.bkav.command.model.time.TimeModel;
import com.bkav.command.test.SampleData;
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
		this.pipeLineModel = new PipeLineModel(new HomeEntityModel(), new ControlModel(), new TimeModel());
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
			pipeLineModel.stream().filter(item -> item instanceof CommonModel<?>).forEach(item -> {
				((CommonModel<?>) item).test(command);
			});
		}
		assertTrue(true);// TODO test modelsTest.
	}
	protected PipeLineModel pipeLineModel;
	protected String[][] commands = CollectionUtil.convert(SampleData.SampleCommands2);
}