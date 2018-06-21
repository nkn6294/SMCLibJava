package com.bkav.command.data;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.SystemManager;
import com.bkav.command.demo.data.HomeTest;
import com.bkav.command.demo.model.HomeEntityModel;
import com.bkav.command.model.PipeLineModel;
import com.bkav.command.model.time.TimeModel;

public class TextWrapperTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.pipeLineModel = new PipeLineModel(new HomeEntityModel(HomeTest.getHomeTest()), new TimeModel());
		this.commands = SampleData.SampleCommands;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testProccess() {
		Arrays.stream(this.commands).map(TextWrapper::new)
				.map(textWrapper -> textWrapper.proccess(pipeLineModel).toString())
				.forEach(SystemManager.logger::info);
		assertTrue(true);// TODO test TextWrapper.process
	}

	private PipeLineModel pipeLineModel;
	private String[] commands;
}
