package com.bkav.command.data.time;

import com.bkav.command.data.CommonData;

public class TimeInDay extends CommonData {
	public static TimeInDay createFromStringArray(String[] datas) {
		return new TimeInDay(getSimpleName(datas));
	}

	public TimeInDay(String name) {
		super(name);
	}
}
