package com.bkav.command.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bkav.command.data.NormalInputUtils;

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
			String output = NormalInputUtils.textToNumber(inputs[index]);
//			SystemManager.logger.info(output);
			assertEquals(expecteds[index], output);
		}
	}

}
