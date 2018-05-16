package com.bkav.command.data;

public class TimeInDay extends CommonData {
	public static TimeInDay createFromStringArray(String[] datas) {
		return new TimeInDay(getSimpleName(datas));
	}

	public TimeInDay(String name) {
		super(name);
	}
}
