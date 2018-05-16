package com.bkav.command.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.model.control.ControlModel;
import com.bkav.command.model.entity.HomeEntityModel;
import com.bkav.command.model.time.TimeModel;
import com.bkav.command.test.SampleData;
import com.bkav.util.CollectionUtil;

public class ModelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testModels() {
		String[][] commands = CollectionUtil.convert(SampleData.SampleCommands2);
		PipeLineModel pipeLineModel = new PipeLineModel(new HomeEntityModel(), new ControlModel(), new TimeModel());

		for (String[] command : commands) {
			System.out.println("-------------------------");
			String commandString = String.join(" ", command);
			System.out.println("<" + commandString + ">");
			pipeLineModel.stream().filter(item -> item instanceof CommonModel<?>).forEach(item -> {
				((CommonModel<?>) item).test(command);
			});
		}
		assertTrue(true);//TODO test modelTest.
	}

}
