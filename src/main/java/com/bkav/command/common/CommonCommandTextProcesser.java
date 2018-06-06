package com.bkav.command.common;

import java.util.List;

import com.bkav.util.StringUtil;

public class CommonCommandTextProcesser implements CommandTextProcesser {
	public CommonCommandTextProcesser(TextProcesser textProcesser) {
		this.textProcesser = textProcesser;
	}
	@Override
	public String apply(String text) {
		return this.textProcesser.apply(text);
	}
	@Override
	public String[] textToWords(String text) {
		return StringUtil.splitString(text);
	}
	@Override
	public List<String> textToListWords(String text) {
		return StringUtil.splitStringToList(text);
	}
	private TextProcesser textProcesser;
}
