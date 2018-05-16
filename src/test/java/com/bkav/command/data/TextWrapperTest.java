package com.bkav.command.data;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.common.Model;
import com.bkav.command.model.PipeLineModel;
import com.bkav.command.model.control.ControlModel;
import com.bkav.command.model.entity.HomeEntityModel;
import com.bkav.command.model.time.TimeModel;
import com.bkav.command.test.SampleData;

public class TextWrapperTest {

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
	public final void testProccess() {
		PipeLineModel pipeLineModel = new PipeLineModel(new HomeEntityModel(), new ControlModel(), new TimeModel());
		String command = SampleData.SampleCommands[0];
		TextWrapper textWrapper = new TextWrapper(command);
		for (Model model : TextWrapper.proccess(textWrapper, pipeLineModel)) {
			System.out.println(model);
		}
		assertTrue(true);// TODO test TextWrapper.process
	}

}
