package com.bkav.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CollectionUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.input = new String[] {
			"00 01 02 03",
			"10 11 12 13",
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertStringArray() {
		String[][] converted = CollectionUtil.convert(this.input);
		for (int i = 0; i < converted.length; i++) {
			for (int j = 0; j < converted[i].length; j++) {
				assertEquals("" + i + j, converted[i][j]);
			}
		}
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

	private String[] input;
}
