package com.bkav.command.data;

public class TimeUnit extends CommonData {
	public static TimeUnit createFromStringArray(String[] datas) {
		return new TimeUnit(getSimpleName(datas));
	}

	public TimeUnit(String name) {
		super(name);
	}
}
