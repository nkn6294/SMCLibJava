package com.bkav.command.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/***
 * 
 * @author NamNK
 * 
 * @ Information current: time, status.
 */
public class Context {
	public long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public LocalDate getNowDate() {
		return LocalDate.now();
	}

	public LocalTime getNow() {
		return LocalTime.now();
	}

	public String getAMOrPM() {
		LocalTime time = this.getNow();
		return time.getHour() >= 12 ? "PM" : "AM";
	}

	public DayOfWeek startOfWeek() {
		return this.startDayOfWeek;
	}
	public void startDayOfWeek(DayOfWeek startDayOfWeek) {
		this.startDayOfWeek = startDayOfWeek;
	}
	protected Context() {
	}
	
	protected DayOfWeek startDayOfWeek = DayOfWeek.MONDAY;
}
