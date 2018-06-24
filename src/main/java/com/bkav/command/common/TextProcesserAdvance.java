package com.bkav.command.common;

import java.util.Iterator;

public interface TextProcesserAdvance extends TextProcesser, Iterable<TextProcesser> {

	default String apply(String text) {
		String output = text;
		Iterator<TextProcesser> iterator = this.iterator();
		if (iterator == null) {
			return output;
		}
		while (iterator.hasNext()) {
			output = iterator.next().apply(output);
		}
		return output;
	}

	public void addTextProcesser(TextProcesser processer);

	public boolean removeTextProcesser(TextProcesser processer);

	public void clear();

}
