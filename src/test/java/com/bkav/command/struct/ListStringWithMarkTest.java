package com.bkav.command.struct;

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

import com.bkav.command.struct.ListStringWithMask;

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
		this.listStringWithMark = new ListStringWithMask(this.sample);
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
	public final void testMarkStream() {
		this.listStringWithMark.setMark(1, 3, 5, 7, 9);
		String[] output = this.listStringWithMark.markString();
		String[] expected = { "1", "3", "5", "7", "9" };
		assertArrayEquals(expected, output);
	}

	@Test
	public final void testUnMarkStream() {
		this.listStringWithMark.setMark(1, 3, 5, 7, 9);
		String[] output = this.listStringWithMark.unMarkString();
		String[] expected = { "0", "2", "4", "6", "8" };
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
		for (String word : this.listStringWithMark) {
			outputList.add(word);
		}
		String[] output = outputList.toArray(new String[outputList.size()]);
		assertArrayEquals(this.listStringWithMark.datas(), output);
	}

	@Test
	public final void testIteratorWithMark() {
		String[] expected = { "1", "3", "5", "7", "9" };
		List<String> expectedList = new ArrayList<>();
		this.listStringWithMark.setMark(0, 2, 4, 6, 8);
		for (String word : this.listStringWithMark) {
			expectedList.add(word);
		}
		String[] output = expectedList.toArray(new String[expectedList.size()]);
		assertArrayEquals(expected, output);
	}

	@Test
	public final void testGetStringFragments() {
		this.listStringWithMark.setMark(1, 2, 6, 7, 8);
		String[][] marks = this.listStringWithMark.getStringFragments();
		assertEquals(2, marks.length);
		assertArrayEquals(marks[0], new String[] { "1", "2" });
		assertArrayEquals(marks[1], new String[] { "6", "7", "8" });
	}

	@Test
	public final void testGetStringUnMarks() {
		this.listStringWithMark.setMark(0, 3, 4, 5, 9);
		String[][] marks = this.listStringWithMark.getStringUnMarks();
		assertEquals(2, marks.length);
		assertArrayEquals(marks[0], new String[] { "1", "2" });
		assertArrayEquals(marks[1], new String[] { "6", "7", "8" });
	}

	@Test
	public final void testGetFragmentsContainIndex() {
		this.listStringWithMark.setMark(1, 2, 3, 7, 8, 9);
		int[][] results = this.listStringWithMark.getFragmentsContainIndex(1, 2, 3, 4, 7, 8);
		assertEquals(6, results.length);
		assertArrayEquals(results[0], new int[] { 1, 2, 3 });
		assertArrayEquals(results[1], new int[] { 1, 2, 3 });
		assertArrayEquals(results[2], new int[] { 1, 2, 3 });
		assertArrayEquals(results[3], new int[] {});
		assertArrayEquals(results[4], new int[] { 7, 8, 9 });
		assertArrayEquals(results[5], new int[] { 7, 8, 9 });
	}
	
	@Test
	public final void testGetFragmentsContainIndexOptimal() {
		this.listStringWithMark.setMark(1, 2, 3, 7, 8, 9);
		int[][] results = this.listStringWithMark.getFragmentsContainIndexOptimal(1, 2, 3, 4, 7, 8);
		assertEquals(2, results.length);
		assertArrayEquals(results[0], new int[] { 1, 2, 3 });
		assertArrayEquals(results[1], new int[] { 7, 8, 9 });
	}
	@Test
	public final void testGetFragmentsContainIndexOptimal2() {
		this.listStringWithMark.setMark(1, 2, 3, 7, 8, 9);
		List<Integer> containsIndex = new ArrayList<>();
		containsIndex.add(1);
		containsIndex.add(2);
		containsIndex.add(3);
		containsIndex.add(4);
		containsIndex.add(7);
		containsIndex.add(8);
		int[][] results = this.listStringWithMark.getFragmentsContainIndexOptimal(containsIndex);//1, 2, 3, 4, 7, 8);
		assertEquals(2, results.length);
		assertArrayEquals(results[0], new int[] { 1, 2, 3 });
		assertArrayEquals(results[1], new int[] { 7, 8, 9 });
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
		int[] expected = new int[] { 0, 1 };
		int[] output = this.listStringWithMark.getFragmentIndex(0);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(1);
		assertArrayEquals(expected, output);

		expected = new int[0];
		output = this.listStringWithMark.getFragmentIndex(2);
		assertArrayEquals(expected, output);

		expected = new int[] { 3, 4 };
		output = this.listStringWithMark.getFragmentIndex(3);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(4);
		assertArrayEquals(expected, output);

		expected = new int[] { 7, 8, 9 };
		output = this.listStringWithMark.getFragmentIndex(7);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(8);
		assertArrayEquals(expected, output);
		output = this.listStringWithMark.getFragmentIndex(9);
		assertArrayEquals(expected, output);
	}

	@Test
	public final void testResetFragment() {
		this.listStringWithMark.setMark(0, 1, 2, 3, 4, 7, 8, 9);
		this.listStringWithMark.resetFragment(0);
		for (int i = 0; i <= 3; i++) {
			assertTrue(this.listStringWithMark.isUnMark(i));
		}
		
		this.listStringWithMark.reset();
		this.listStringWithMark.setMark(0, 1, 2, 3, 4, 7, 8, 9);
		this.listStringWithMark.resetFragment(2);
		for (int i = 0; i <= 3; i++) {
			assertTrue(this.listStringWithMark.isUnMark(i));
		}
		
		this.listStringWithMark.reset();
		this.listStringWithMark.setMark(0, 1, 2, 3, 4, 7, 8, 9);
		this.listStringWithMark.resetFragment(3);
		for (int i = 0; i <= 3; i++) {
			assertTrue(this.listStringWithMark.isUnMark(i));
		}
		
		this.listStringWithMark.reset();
		this.listStringWithMark.setMark(0, 1, 2, 3, 4, 7, 8, 9);
		this.listStringWithMark.resetFragment(4);
		for (int i = 0; i <= 3; i++) {
			assertTrue(this.listStringWithMark.isUnMark(i));
		}
	}
	
	@Test
	public final void testRelativeMarkIndex() {
		this.listStringWithMark.setMark(0, 1, 2, 7, 8);
		int[] unmarksIndex = this.listStringWithMark.unMarkIndexs();//
		int[] marksIndex = this.listStringWithMark.markIndexs();
		assertArrayEquals(new int[] {3, 4, 5, 6, 9}, unmarksIndex);
		assertArrayEquals(new int[] {0, 1, 2, 7, 8}, marksIndex);
		
		int[] relativeUnMarksIndex = new int[] {0, 1, 3};
		this.listStringWithMark.setMarkWithRelativeIndex(relativeUnMarksIndex);
		unmarksIndex = this.listStringWithMark.unMarkIndexs();
		assertArrayEquals(new int[] {5, 9}, unmarksIndex);
	}
	
	private ListStringWithMask listStringWithMark;
	private String[] sample;
}
