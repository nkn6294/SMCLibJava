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
	public final void testTextToNumber() {
		String[] inputs = new String[] { 
				"năm nhân bốn cộng một bằng hai mươi mốt", 
				"mười hai", 
				"một trăm hai mươi",
				"tám tư cộng một trăm tám mốt bằng hai trăm sáu năm",
				"hai mươi tư",
				"mười day",
				"10 một"
				};
		String[] expecteds = new String[] { 
				"5 nhân 4 cộng 1 bằng 21", 
				"12", 
				"120",
				"84 cộng 181 bằng 265",
				"24",
				"10 day",
				"11"
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = StringUtil.textToNumber(inputs[index]);
//			SystemManager.logger.info(output);
			assertEquals(expecteds[index], output);
		}
	}
	@Test
	public final void testTimeToNormal() {
		String[] inputs = new String[] { 
				"6 am",
				"6am",
				"12 a. m",
				"01 a . m",
				"2 giờ",
				"2 giờ pm",
				"2 giờ 10 phút",
				"2 giờ 10 phút",
				};
		String[] expecteds = new String[] { 
				"6:00",
				"6:00",
				"12:00",
				"01:00",
				"2:00",
				"14:00",
				"2:10",
				"2:10",
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = StringUtil.timeToNormal(inputs[index]);
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
