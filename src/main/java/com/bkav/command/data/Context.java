package com.bkav.command.data;

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

	protected Context() {
	}
}
