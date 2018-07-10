package com.bkav.command.model.time;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bkav.command.SystemManager;

public class DateUtilsTest {

	@Test
	public void testDateToNormal() {
		String[] inputs = new String[] { 
			"s ngày 12 tháng 2 năm 2019 e",
			"s ngày 12 tháng 2 e",
			"s 12 tháng 4 e",
			"s ngày 12 e",
			"s ngày 12-12-2012 e",
			"s ngày 12/12/2012 e",
			"s ngày 12_12_2012 e",
//			"2 giờ 10 phút ngày 12-12-2012",
		};
		String[] expecteds = new String[] { 
			"12-2-2019",
			"12-2-0000",
			"12-4-0000",
			"12-00-0000",
			"12-12-2012",
			"12-12-2012",
			"12-12-2012",
//			"2 giờ 10 phút 12-12-2012",
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = DateUtils.dateToNormal(inputs[index]);
			SystemManager.logger.info(output);
			assertEquals("s _date(" + expecteds[index] + ") e", output);
//			assertEquals(expecteds[index], output);
		}
	}
	@Test
	public void testDateToNormal2() {
		String[] inputs = new String[] { 
//			"thứ 6 ngày 12",
//			"chủ nhật",
			"chủ nhật tuần này",
			"chủ nhật tuần sau",
//			"tuần 1 tháng 2",
//			"thứ 6 hàng tuần",
//			"thứ 6 tuần 1 tháng 2",
			"ngày mai",
			"hôm nay",
		};
		String[] expecteds = new String[] {
//			"_e6 12-00-0000",
//			"_e8 12-00-0000",
			"_e8 _w+0",
			"_e8 _w+1",
//			"_w#1_m2",
//			"_e6 hàng tuần",
//			"_e6 _w#1_m2",
			"_d+1",
			"_d+0"
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = DateUtils.dateToNormal(inputs[index]);
			SystemManager.logger.info(output);
			assertEquals(expecteds[index], output);
//			assertEquals(expecteds[index], output);
		}
	}
}
