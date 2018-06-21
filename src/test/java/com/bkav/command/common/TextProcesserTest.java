package com.bkav.command.common;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.bkav.command.SystemManager;
import static com.bkav.command.SystemManager.*;

public class TextProcesserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.textProcesser = SystemManager.textProcesser;
	}

	@After
	public void tearDown() throws Exception {
	}

	protected CommandTextProcesser textProcesser;
	
//	@Test
//	public final void testPreProccessText() {
//		String input = "đây là phòng ăn";
//		String output = "day la phong an";
//		SystemManager.logger.info(this.textProcesser.apply(input));
//		assertEquals(output, this.textProcesser.apply(input));
//	}

//	@Test
//	public final void testTextToWords() {
//		String input = "day la phong an";
//		String[] expected = {"day", "la", "phong", "an"};
//		assertArrayEquals(expected, this.textProcesser.textToWords(input));
//	}
//
//	@Test
//	public final void testTextToListWords() {
//		String input = "day la phong an";
//		List<String> output = this.textProcesser.textToListWords(input);
//		Stream.of("day", "la", "phong", "an").forEach(output::remove);
//		assertTrue(output.size() == 0);
//	}

	protected final void testItemProcesser(String[] inputs, String[] expecteds, Function<String, String> processer) {
		for (int index = 0; index < inputs.length; index++) {
			String input = inputs[index];
			String expected = expecteds[index];
			String output = processer.apply(input);
			logger.info(String.format("%s ---> %s", input, output));
			assertEquals(expected, output);
		}
	}
}
