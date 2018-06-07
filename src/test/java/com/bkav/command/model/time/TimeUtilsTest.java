package com.bkav.command.model.time;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bkav.command.SystemManager;

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
				"+2:10",
				"+2:10",
				"00:2",
				"+00:2",
				"+00:02",
				"2:10"
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = TimeUtils.timeToNormal(inputs[index]);
			SystemManager.logger.info(output);
			assertEquals("_t_(" + expecteds[index] + ")", output);
		}
	}
}
