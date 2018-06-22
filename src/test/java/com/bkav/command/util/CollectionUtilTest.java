package com.bkav.command.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.util.CollectionUtil;

public class CollectionUtilTest {

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
	public void testConvertStringArray() {
		String[] input = new String[] {
				"00 01 02 03 04",
				"10 11 12 13 14",
				"20 21 22 23 24"
		};
		String[][] converted = CollectionUtil.convert(input);
		for (int i = 0; i < converted.length; i++) {
			for (int j = 0; j < converted[i].length; j++) {
				assertEquals("" + i + j, converted[i][j]);
			}
		}
	}
	@Test
	public void testListToArray() {
		ArrayList<String> item0 = new ArrayList<>();
		item0.add("00");
		item0.add("01");
		item0.add("02");
		ArrayList<String> item1 = new ArrayList<>();
		item1.add("10");
		item1.add("11");
		item1.add("12");
		
		List<List<String>> input = new ArrayList<>();
		input.add(item0);
		input.add(item1);
		
		String[][] output = CollectionUtil.toArray(input);
		
		assertEquals(2, output.length);
		assertArrayEquals(new String[] {"00", "01", "02"}, output[0]);
		assertArrayEquals(new String[] {"10", "11", "12"}, output[1]);
	}
	
	@Test
	public final void testConvertStringArrayTextProcesser() {
		assertTrue(true); //TODO testConvertStringArrayTextProcesser
	}

	@Test
	public final void testConvertListOfString() {
		assertTrue(true); //TODO testConvertListOfString
	}

	@Test
	public final void testConvertListOfStringTextProcesser() {
		assertTrue(true); //TODO testConvertListOfStringTextProcesser
	}
}
