package com.bkav.command.data;

public class TimeRepeat extends CommonData {
	public static TimeRepeat createFromStringArray(String[] datas) {
		return new TimeRepeat(getSimpleName(datas));
	}

	public TimeRepeat(String name) {
		super(name);
	}
}
