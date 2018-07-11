package com.bkav.command.model.time;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bkav.command.SystemManager;

public class DateUtilsTest {

	@Test
	public void testDateToNormal() {
		String[] inputs = new String[] { 
			"ngày 12 tháng 2 năm 2019",
			"ngày 12 tháng 2",
			"12 tháng 4",
			"ngày 12",
			"ngày 12-12-2012",
			"ngày 12/12/2012",
			"ngày 12_12_2012",
			
			"chủ nhật",
			"thứ 2",
			"tuần này",
			"tuần tới",
			"hàng tuần",
		};
		String[] expecteds = new String[] { 
			"12-2-2019",
			"12-2-0000",
			"12-4-0000",
			"12-00-0000",
			"12-12-2012",
			"12-12-2012",
			"12-12-2012",
			
			"_e+8",
			"_e+2",
			"_w+0",
			"_w+1",
			"_w+*",
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = DateUtils.dateToNormal("< " + inputs[index] + " >");
			SystemManager.logger.info(output);
			assertEquals("< _date(" + expecteds[index] + ") >", output);
		}
	}
}
