package com.bkav.command.model.time;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bkav.command.SystemManager;

public class TimeUtilsTest {

	@Test
	public final void testTimeToNormal() {
		String[] inputs = new String[] { 
				"s 6 am e",
				"s 6am e",
				"s 12 a. m e",
				"s 01 a . m e",
				"s 2 giờ e",
				
				"s 2 giờ pm e",
				"s 2 giờ 10 phút e",
				"s 2 giờ 10 phút e",
				"s 2 giờ 10 phút nữa e",
				"s sau 2 giờ 10 phút nữa e",
				
				"s 2 phút e",
				"s 2 phút nữa e",
				"s sau 02 phút nữa e",
				"s lúc 2 giờ 10 e",
				"s 6 giờ 30 am e",
				
				"s 6 giờ 30 pm e",
				"s 6 giờ sáng e",
				"s 3 giờ chiều e",
				"s 11 giờ trưa e",
				"s 11 giờ 30 đêm e",
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
				"2:10",
				"6:30",
				
				"18:30",
				"6:00",
				"15:00",
				"11:00",
				"23:30",
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = TimeUtils.timeToNormal(inputs[index]);
			SystemManager.logger.info(output);
			assertEquals("s _time(" + expecteds[index] + ") e", output);
		}
	}
}
