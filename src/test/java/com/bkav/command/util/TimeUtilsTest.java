package com.bkav.command.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bkav.command.SMCManager;
import com.bkav.command.util.TimeUtils;

public class TimeUtilsTest {

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
				"2 giờ 10 phút nữa",
				"sau 2 giờ 10 phút nữa",
				
				"2 phút",
				"2 phút nữa",
				"sau 02 phút nữa",
				"lúc 2 giờ 10",
				"6 giờ 30 am",
				
				"6 giờ 30 pm",
				"6 giờ sáng",
				"3 giờ chiều",
				"11 giờ trưa",
				"11 giờ 30 đêm",
				
				};
		String[] expecteds = new String[] { 
				"6:00a",
				"6:00a",
				"12:00a",
				"01:00a",
				"2:00",
				
				"14:00",
				"2:10",
				"2:10",
				"+2:10",
				"+2:10",
				
				"00:2",
				"+00:2",
				"+00:02",
				"2:10",
				"6:30a",
				
				"18:30",
				"6:00a",
				"15:00",
				"11:00a",
				"23:30",
				
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = TimeUtils.timeToNormal("< " + inputs[index] + " >");
			SMCManager.logger.info(output);
			assertEquals("< _time(" + expecteds[index] + ") >", output);
		}
	}
}
