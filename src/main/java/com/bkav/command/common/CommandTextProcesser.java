package com.bkav.command.common;

import java.util.List;

public interface CommandTextProcesser extends TextProcesser {
	
	public String[] textToWords(String text);
	
	public List<String> textToListWords(String text);
	
	public String joinString(String[] words);
}
