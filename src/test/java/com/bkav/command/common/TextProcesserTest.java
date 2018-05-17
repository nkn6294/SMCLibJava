package com.bkav.command.common;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextProcesserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.textProcesser = new TextProcesser() {
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testPreProccessText() {
		String input = "đây là phòng ăn";
		String output = "day la phong an";
		System.out.println(this.textProcesser.preProccessText(input));
		assertEquals(output, this.textProcesser.preProccessText(input));
	}

	@Test
	public final void testTextToWords() {
		String input = "day la phong an";
		String[] expected = {"day", "la", "phong", "an"};
		assertArrayEquals(expected, this.textProcesser.textToWords(input));
	}

	@Test
	public final void testTextToListWords() {
		String input = "day la phong an";
		String[] expected = {"day", "la", "phong", "an"};
		List<String> output = this.textProcesser.textToListWords(input);
		Arrays.stream(expected).forEach(output::remove);
		assertTrue(output.size() == 0);
	}

	private TextProcesser textProcesser;
}
