package com.bkav.struct;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
		IntStream.range(0,  this.sample.length).forEach(index -> {
			assertEquals(this.sample[index], this.listStringWithMark.get(index));			
		});
	}
	@Test
	public final void testStream() {
		this.listStringWithMark.setMark(1, 3, 5, 7, 9);
		String[] output = this.listStringWithMark.stream().toArray(String[]::new);
		String[] expected = {"0", "2", "4", "6", "8"};
		assertArrayEquals(expected, output);
	}
	
	@Test
	public final void testMarkStream() {
		this.listStringWithMark.setMark(1, 3, 5, 7, 9);
		String[] output = this.listStringWithMark.unMarkStream().toArray(String[]::new);
		String[] expected = {"0", "2", "4", "6", "8"};
		assertArrayEquals(expected, output);
	}
	
	@Test
	public final void testUnMarkStream() {
		this.listStringWithMark.setMark(1, 3, 5, 7, 9);
		String[] output = this.listStringWithMark.markStream().toArray(String[]::new);
		String[] expected = {"1", "3", "5", "7", "9"};
		assertArrayEquals(expected, output);
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
		assertArrayEquals(this.listStringWithMark.strings(), output);
	}
	
	@Test
	public final void testIteratorWithMark() {
		String[] expected = {"1", "3", "5", "7", "9"};
		List<String> expectedList = new ArrayList<>();
		this.listStringWithMark.setMark(0, 2, 4, 6, 8);
		this.listStringWithMark.forEach(expectedList::add);
		String[] output = expectedList.toArray(new String[expectedList.size()]);
		assertArrayEquals(expected, output);
	}
	
	@Test
	public final void testGetMarks() {
		this.listStringWithMark.setMark(1, 2, 6, 7, 8);
		String[][] marks = this.listStringWithMark.getMarks();
		assertEquals(2, marks.length);
		assertArrayEquals(marks[0], new String[] {"1", "2"});
		assertArrayEquals(marks[1], new String[] {"6", "7", "8"});
	}
	
	@Test
	public final void testGetUnMarks() {
		this.listStringWithMark.setMark(0, 3, 4, 5, 9);
		String[][] marks = this.listStringWithMark.getUnMarks();
		assertEquals(2, marks.length);
		assertArrayEquals(marks[0], new String[] {"1", "2"});
		assertArrayEquals(marks[1], new String[] {"6", "7", "8"});
	}
	private ListStringWithMark listStringWithMark;
	private String[] sample;
}
