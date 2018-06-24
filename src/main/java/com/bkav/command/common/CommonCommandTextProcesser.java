package com.bkav.command.common;

import java.util.List;

import com.bkav.command.util.StringUtil;

public class CommonCommandTextProcesser implements CommandTextProcesser {
	public CommonCommandTextProcesser(TextProcesser textProcesser) {
		this.textProcesser = textProcesser;
	}
	public CommonCommandTextProcesser() {
		this(null);
	}
	@Override
	public String apply(String text) {
		if (this.textProcesser == null) {
			return text;
		}
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

	@Override
	public String joinString(String[] words) {
		return StringUtil.joinString(words);
	}
	
	protected TextProcesser textProcesser;
}
