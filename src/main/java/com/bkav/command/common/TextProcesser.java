package com.bkav.command.common;

import java.util.List;

import com.bkav.util.StringUtil;

public interface TextProcesser {
	default public String preProccessText(String text) {
		return StringUtil.deAccentConvert(text.toLowerCase().replace('đ', 'd'));
	};
	
	default public String[] textToWords(String text) {
		return StringUtil.splitString(text);
	}
	default public List<String> textToListWords(String text) {
		return StringUtil.splitStringToList(text);
	}
}
