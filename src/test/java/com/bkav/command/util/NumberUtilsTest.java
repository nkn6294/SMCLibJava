package com.bkav.command.util;

import org.junit.Test;

import com.bkav.command.common.Function;
import com.bkav.command.common.TextProcesserTest;
import com.bkav.command.util.NumberUtils;

public class NumberUtilsTest extends TextProcesserTest {

	@Test
	public final void testTextToNumber() {
		String[] inputs = new String[] { 
				"năm nhân bốn cộng một bằng hai mươi mốt", 
				"mười hai", 
				"một trăm hai mươi",
				"tám tư cộng một trăm tám mốt bằng hai trăm sáu năm",
				"hai mươi tư",
				"mười day",
				"10 một",
				"hai mốt",
				"một trăm mười",
				"Tăng điều hòa một mười độ",
				};
		String[] expecteds = new String[] { 
				"5 nhân 4 cộng 1 bằng 21", 
				"12", 
				"120",
				"84 cộng 181 bằng 265",
				"24",
				"10 day",
				"11",
				"21",
				"110",
				"Tăng điều hòa 1 10 độ"
		};
		this.testItemProcesser(inputs, expecteds, new Function<String, String>() {
			
			@Override
			public String apply(String value) {
				return NumberUtils.textToNumber(value);
			}
		});
	}
}
