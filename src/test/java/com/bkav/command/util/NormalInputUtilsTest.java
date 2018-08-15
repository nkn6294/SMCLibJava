package com.bkav.command.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bkav.command.util.NormalInputUtils;

public class NormalInputUtilsTest {

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
			String output = NormalInputUtils.deAccentConvert(inputs[index]);
			assertEquals(expecteds[index], output);//TODO testDeAccentConvert more case
		}
	}
}
