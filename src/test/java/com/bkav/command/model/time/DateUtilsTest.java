package com.bkav.command.model.time;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bkav.command.SystemManager;

public class DateUtilsTest {

	@Test
	public void testDateToNormal() {
		String[] inputs = new String[] { 
//			"ngày 12 tháng 2 năm 2019",
//			"ngày 12 tháng 2",
//			"12 tháng 4",
//			"ngày 12",
//			"thứ 6 ngày 12",
//			"chủ nhật ngày 12",
//			"chủ nhật tuần này",
//			"chủ nhật tuần sau",
//			"tuần 1 tháng 2",
//			"thứ 6 hàng tuần",
//			"ngày 12-12-2012",
//			"ngày 12/12/2012",
//			"ngày 12_12_2012",
			"2 giờ 10 phút ngày 12-12-2012"
		};
		String[] expecteds = new String[] { 
//			"12-2-2019",
//			"12-2-0000",
//			"12-4-0000",
//			"12-00-0000",
//			"_e6 12-00-0000",
//			"_e8 12-00-0000",
//			"_e8 _w+0",
//			"_e8 _w+1",
//			"_w#1_m2",
//			"_e6 hàng tuần",
//			"12-12-2012",
//			"12-12-2012",
//			"12-12-2012",
			"2 giờ 10 phút 12-12-2012",
		};
		for (int index = 0; index < inputs.length; index++) {
			String output = DateUtils.dateToNormal(inputs[index]);
			SystemManager.logger.info(output);
//			assertEquals("_d_(" + expecteds[index] + ")", output);
			assertEquals(expecteds[index], output);
		}
	}

}
