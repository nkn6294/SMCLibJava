package com.bkav.command.common;

public interface TextProcesserAdvance extends TextProcesser, Iterable<TextProcesser> {

	public void addTextProcesser(TextProcesser processer);

	public boolean removeTextProcesser(TextProcesser processer);

	public void clear();

}
