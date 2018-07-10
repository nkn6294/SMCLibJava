package com.bkav.command.data.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;

public class DateValue {
	public enum DateValueType {
		FULL_VALUE,
		MONTH_DAY,
		DAY_OF_WEEK,
	}
	
	public DateValue(LocalDate date, MonthDay monthDay, DayOfWeek dayOfWeek, Period period) {
		this.date = date;
		this.monthDay = monthDay;
		this.dayOfWeek = dayOfWeek;
		this.period = period;
		this.updateMode();
	}
	
	public DateValue(MonthDay monthDay) {
		this(null, monthDay, null, null);
	}
	
	public DateValue(LocalDate date) {
		this(date, null, null, null);
	}
	
	public LocalDate date() {
		return this.date;
	}
	
	public MonthDay monthDay() {
		return this.monthDay;
	}
	
	public DayOfWeek dayOfWeek() {
		return this.dayOfWeek;
	}
	
	public Period period() {
		return this.period;
	}
	public DateValueType mode() {
		return this.mode;
	}
	
	protected MonthDay monthDay;
	protected LocalDate date;
	protected DayOfWeek dayOfWeek;
	protected DateValueType mode;
	protected Period period;
	
	protected void updateMode() {
		if (this.date != null) {
			this.mode = DateValueType.FULL_VALUE;
		} else if(this.monthDay != null) {
			this.mode = DateValueType.MONTH_DAY;
		} else if (this.dayOfWeek != null) {
			this.mode = DateValueType.DAY_OF_WEEK;
		}
	}
}
