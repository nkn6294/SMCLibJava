package com.bkav.command.data.time;

public class DayOfMonth {
	public static DayOfMonth of(int dayOfMonth) {
		return new DayOfMonth(dayOfMonth);
	}
	
	public DayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	
	public int dayOfMonth() {
		return this.dayOfMonth;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [dayOfMonth=" + dayOfMonth + "]";
	}

	protected int dayOfMonth;
}
