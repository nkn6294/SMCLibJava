package com.bkav.command.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CommonTextProcesserAdvance extends CommonCommandTextProcesser implements CommandTextProcesserAdvance {
	public CommonTextProcesserAdvance() {
		this.processers = new LinkedList<>();
	}

	@Override
	public void addTextProcesser(TextProcesser processer) {
		this.processers.add(processer);
	}
	@Override
	public String apply(String text) {
		String output = text;
		Iterator<TextProcesser> iterator = this.processers.iterator();
		if (iterator == null) {
			return output;
		}
		while (iterator.hasNext()) {
			output = iterator.next().apply(output);
		}
		return output;
	}
	@Override
	public boolean removeTextProcesser(TextProcesser processer) {
		return this.processers.remove(processer);
	}

	@Override
	public void clear() {
		this.processers.clear();
	}

	@Override
	public Iterator<TextProcesser> iterator() {
		return this.processers.iterator();
	}

	protected List<TextProcesser> processers;
}
