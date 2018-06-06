package com.bkav.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.SystemManager;

public class StringUtilTest {

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
	public final void testDeAccentConvert() {
		String[] inputs = new String[] {
			"hôm nay là thứ sáu",
			"phòng khách",
//			"đi ngủ",
		};
		
		String[] expecteds = new String[] {
			"hom nay la thu sau",
			"phong khach",
//			"di ngu"
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = StringUtil.deAccentConvert(inputs[index]);
			assertEquals(expecteds[index], output);//TODO testDeAccentConvert more case
		}
	}

	@Test
	public final void testTextx1ToNumber2() {
		String input = "2 mươi mốt";
		String output = StringUtil.textx1ToNumber2(input);
		assertEquals("21", output);
	}
	@Test
	public final void testText1xToNumber() {
		String input = "mười 1";
		String output = StringUtil.text1xToNumber(input);
		assertEquals("11", output);
	}
	@Test
	public final void testTextx1ToNumber() {
		String input = "2 mốt";
		String output = StringUtil.textx1ToNumber(input);
		assertEquals("21", output);
	}
	@Test
	public final void testTextx0ToNumber2() {
		String input = "2 mươi nho hon 3 mươi";
		String output = StringUtil.textx0ToNumber(input);
		assertEquals("20 nho hon 30", output);
	}
	@Test
	public final void testTextx00ToNumber2() {
		String input = "day la 2 trăm lon hon";
		String output = StringUtil.textx00ToNumber(input);
		assertEquals("day la 200 lon hon", output);
	}
	@Test
	public final void testTextxxxToNumber2() {
		String input = "day la 200 30 lon hon";
		String output = StringUtil.textxxxToNumber(input);
		SystemManager.logger.info(output);
		assertEquals("day la 230 lon hon", output);
	}
	@Test
	public final void testTextxxToNumber() {
		String input = "day la 2 3 lon hon";
		String output = StringUtil.textxxToNumber(input);
		SystemManager.logger.info(output);
		assertEquals("day la 23 lon hon", output);
	}
	@Test
	public final void testTextToNumber() {
		String[] inputs = new String[] { 
				"năm nhân bốn cộng một bằng hai mươi mốt", 
				"mười hai", 
				"một trăm hai mươi",
				"tám tư cộng một trăm tám mốt bằng hai trăm sáu năm",
				"hai mươi tư",
				};
		String[] expecteds = new String[] { 
				"5 nhân 4 cộng 1 bằng 21", 
				"12", 
				"120",
				"84 cộng 181 bằng 265",
				"24",
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = StringUtil.textToNumber(inputs[index]);
			SystemManager.logger.info(output);
			assertEquals(expecteds[index], output);
		}
	}
	@Test
	public final void testCheckSameStringArrayStringArray() {
		assertTrue(true); // TODO testCheckSameStringArrayStringArray
	}

	@Test
	public final void testCheckSameStringString() {
		assertTrue(true); // TODO testCheckSameStringString
	}

	@Test
	public final void testCheckSameStringStringArray() {
		assertTrue(true); // TODO testCheckSameStringStringArray
	}

	@Test
	public final void testSplitString() {
		assertTrue(true); // TODO testSplitString
	}

	@Test
	public final void testSplitStringToList() {
		assertTrue(true); // TODO testSplitStringToList
	}

	@Test
	public final void testSearchStringString() {
		assertTrue(true); // TODO testSearchStringString
	}

	@Test
	public final void testSearchStringStringArray() {
		assertTrue(true); // TODO testSearchStringStringArray
	}

	@Test
	public final void testSearchByIndex() {
		assertTrue(true); // TODO testSearchStringStringArray
	}

}
