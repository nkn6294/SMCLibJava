package com.bkav.command.struct.mask;

import java.util.List;

import com.bkav.command.util.CollectionUtil;

public class ListStringWithMask extends DatasWithMask<String> implements Iterable<String> {
	
	public ListStringWithMask(String[] strings) {
		super(strings);
	}
	
	public String[][] getStringFragments() {
		return CollectionUtil.toArray(this.getFragments());
	}

	public String[][] getStringUnMarks() {
		return CollectionUtil.toArray(this.getUnMarks());
	}

	public String[] getStringFragment(int containIndex) {
		List<String> fragment = this.getFragment(containIndex);
		return fragment.toArray(new String[fragment.size()]);
	}

	public String[] getStringFragmentStartAt(int startIndexInclusive) {
		List<String> fragment = this.getFragmentStartAt(startIndexInclusive);
		return fragment.toArray(new String[fragment.size()]);
	}

	public String[] getStringFragmenEndAt(int endIndexInclusive) {
		List<String> fragment = this.getFragmenEndAt(endIndexInclusive);
		return fragment.toArray(new String[fragment.size()]);
	}

	@Override
	protected Mask createMask(int length, MaskConfig config) {
		return new IntMask(length, config);
	}

}
