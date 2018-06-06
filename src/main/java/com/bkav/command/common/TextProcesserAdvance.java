package com.bkav.command.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.bkav.util.StringUtil;

public class TextProcesserAdvance implements TextProcesser, Iterable<TextProcesser> {

	public TextProcesserAdvance() {
		this.processers = new LinkedList<>();
	}
	
	public void addTextProcesser(TextProcesser processer) {
		this.processers.add(processer);
	}
	public boolean removeTextProcesser(TextProcesser processer) {
		return this.processers.remove(processer);
	}
	public void clear() {
		this.processers.clear();
	}
	public String apply(String text) {
		String output = text;
		Iterator<TextProcesser> iterator = this.processers.iterator();
		while(iterator.hasNext()) {
			output = iterator.next().apply(output);
		}
		return output;
	}
	
	public String[] textToWords(String text) {
		return StringUtil.splitString(text);
	}
	public List<String> textToListWords(String text) {
		return StringUtil.splitStringToList(text);
	}
	
	@Override
	public Iterator<TextProcesser> iterator() {
		return this.processers.iterator();
	}
	
	protected List<TextProcesser> processers;

}
