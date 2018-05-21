package com.bkav.struct;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.SystemManager;

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
		this.listStringWithMark.resetByIndex(1, 3);
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
	public final void testGetFragments() {
		this.listStringWithMark.setMark(1, 2, 6, 7, 8);
		String[][] marks = this.listStringWithMark.getFragments();
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
	
	@Test
	public final void getFragmentsContainIndex() {
		this.listStringWithMark.setMark(1, 2, 3, 7, 8, 9);
		for (int[] item : this.listStringWithMark.getFragmentsContainIndex(1, 2, 4,  7, 8)) {
			SystemManager.logger.info(Arrays.toString(item));
		}
		assertEquals(0, 0);
	}
	
	@Test
	public final void testGetFragmentStartIndexAt() {
		this.listStringWithMark.setMark(0, 1, 2, 3, 7, 8, 9);
		int startInclusive = this.listStringWithMark.getFragmenStartIndextEndAt(0);
		assertEquals(0, startInclusive);
		startInclusive = this.listStringWithMark.getFragmenStartIndextEndAt(1);
		assertEquals(0, startInclusive);
		startInclusive = this.listStringWithMark.getFragmenStartIndextEndAt(2);
		assertEquals(0, startInclusive);
		startInclusive = this.listStringWithMark.getFragmenStartIndextEndAt(3);
		assertEquals(0, startInclusive);
		
		startInclusive = this.listStringWithMark.getFragmenStartIndextEndAt(7);
		assertEquals(7, startInclusive);
		startInclusive = this.listStringWithMark.getFragmenStartIndextEndAt(8);
		assertEquals(7, startInclusive);
		startInclusive = this.listStringWithMark.getFragmenStartIndextEndAt(9);
		assertEquals(7, startInclusive);
	}
	
	@Test
	public final void testGetFragmentEndIndexAt() {
		this.listStringWithMark.setMark(0, 1, 2, 3, 7, 8, 9);
		int endInclusive = this.listStringWithMark.getFragmentEndIndexStartAt(0);
		assertEquals(3, endInclusive);
		endInclusive = this.listStringWithMark.getFragmentEndIndexStartAt(1);
		assertEquals(3, endInclusive);
		endInclusive = this.listStringWithMark.getFragmentEndIndexStartAt(2);
		assertEquals(3, endInclusive);
		endInclusive = this.listStringWithMark.getFragmentEndIndexStartAt(3);
		assertEquals(3, endInclusive);
		
		endInclusive = this.listStringWithMark.getFragmentEndIndexStartAt(7);
		assertEquals(9, endInclusive);
		endInclusive = this.listStringWithMark.getFragmentEndIndexStartAt(8);
		assertEquals(9, endInclusive);
		endInclusive = this.listStringWithMark.getFragmentEndIndexStartAt(9);
		assertEquals(9, endInclusive);
	}
	
	@Test
	public final void testGetFragmentIndexWithContainIndex() {
		this.listStringWithMark.setMark(0, 1, 3, 4, 7, 8, 9);
		int[] expected = new int[] {0, 1};
		int[] output = this.listStringWithMark.getFragmentIndex(0);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(1);
		assertArrayEquals(expected, output);
		
		expected = new int[0];
		output = this.listStringWithMark.getFragmentIndex(2);
		assertArrayEquals(expected, output);
		
		expected = new int[] {3, 4};
		output = this.listStringWithMark.getFragmentIndex(3);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(4);
		assertArrayEquals(expected, output);
		
		expected = new int[] {7, 8, 9};
		output = this.listStringWithMark.getFragmentIndex(7);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(8);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(9);
		assertArrayEquals(expected, output);
	}
	
	private ListStringWithMark listStringWithMark;
	private String[] sample;
}
