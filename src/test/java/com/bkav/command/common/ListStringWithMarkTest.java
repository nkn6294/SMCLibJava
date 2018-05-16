package com.bkav.command.common;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListStringWithMarkTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.sample = "0,1,2,3,4,5,6,7,8,9".split(",");
		this.listStringWithMark = new ListStringWithMark(this.sample);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testLength() {
		assertEquals(this.sample.length, this.listStringWithMark.length());
	}

	@Test
	public final void testGet() {
		for (int index = 0; index < this.sample.length; index++) {
			assertEquals(this.sample[index], this.listStringWithMark.get(index));
		}
	}

	@Test
	public final void testMark() {
		this.listStringWithMark.setMark(1, 2, 3);
		this.listStringWithMark.reset(1, 3);
		assertFalse(this.listStringWithMark.isMark(1));
		assertTrue(this.listStringWithMark.isMark(2));
		assertFalse(this.listStringWithMark.isMark(3));
	}

	@Test
	public final void testIterator() {
		List<String> outputList = new ArrayList<>();
		this.listStringWithMark.forEach(outputList::add);
		String[] output = outputList.toArray(new String[outputList.size()]);
		assertArrayEquals(this.listStringWithMark.getStrings(), output);
	}
	
	@Test
	public final void testIteratorWithMark() {
		String[] expected = {"1", "3", "5", "7", "9"};
		this.listStringWithMark.setMark(0, 2, 4, 6, 8);
		List<String> outputList = new ArrayList<>();
		this.listStringWithMark.forEach(outputList::add);
		String[] output = outputList.toArray(new String[outputList.size()]);
		assertArrayEquals(expected, output);
	}
	
	private ListStringWithMark listStringWithMark;
	private String[] sample;
}
