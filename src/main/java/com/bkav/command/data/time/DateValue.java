package com.bkav.command.data.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;

public class DateValue {
	public enum DateValueType {
		FULL_VALUE,
		MONTH_AND_DAY,
		MONTH_DAY,
		DAY_OF_WEEK,
		NONE
	}
	
	public DateValue(LocalDate date, MonthDay monthDay, DayOfWeek dayOfWeek, Period period) {
		this.localDate = date;
		this.monthDay = monthDay;
		this.dayOfWeek = dayOfWeek;
		this.period = period;
		this.normal();
		this.updateMode();
	}
	
	public DateValue(MonthDay monthDay) {
		this(null, monthDay, null, null);
	}
	
	public DateValue(LocalDate date) {
		this(date, null, null, null);
	}

	/***
	 * Get {@linkplain LocalDate date} with param in this. Return value canable
	 * before now date so need check before using.
	 * 
	 * @return
	 */
	public LocalDate date() {
		LocalDate localDate = LocalDate.now();
		if (this.localDate != null) {
			localDate = this.localDate;			
		} else if (this.monthDay != null) {	
			localDate = LocalDate.of(localDate.getYear(), 
					this.monthDay.getMonth(), this.monthDay.getDayOfMonth());
		} else if (this.dayOfMonth != null) {
			localDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), this.dayOfMonth.dayOfMonth());
		} else if (this.dayOfWeek != null) {
			int current = localDate.getDayOfWeek().getValue();
			int setValue = this.dayOfWeek.getValue();
			localDate = localDate.plusDays(setValue - current);//DayOfWeek in current week (in localDate)
//			if (current > setValue) {
//				localDate = localDate.plusDays(setValue + 7 - current);
//				if (this.period != null) {
//					localDate = localDate.minusDays(this.period.getDays());
//				}
//			};
		}
		if (this.period != null) {
			localDate.plus(this.period);
		}
		return localDate;
	}
	
	public MonthDay monthDay() {
		if (this.monthDay != null) {
			return this.monthDay;
		}
		LocalDate date = this.date();
		return MonthDay.of(date.getMonth(), date.getDayOfMonth());
	}
	public DayOfMonth dayOfMonth() {
		if (this.dayOfMonth != null) {
			return this.dayOfMonth;
		}
		return DayOfMonth.of(this.date().getDayOfMonth());
	}
	public DayOfWeek dayOfWeek() {
		if (this.dayOfWeek != null) {
			return this.dayOfWeek;
		}
		return this.date().getDayOfWeek();
	}
	
	public Period period() {
		if (this.period != null) {
			return this.period;			
		}
		return null;
	}
	public DateValueType mode() {
		return this.mode;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [monthDay=" + monthDay + ", date=" + localDate + ", dayOfWeek=" + dayOfWeek + ", mode=" + mode
				+ ", period=" + period + "]";
	}

	public boolean isValid() {
		boolean isValid = false;
		isValid = 
				this.localDate != null
				|| this.monthDay != null
				|| this.dayOfMonth != null
				|| this.dayOfWeek != null;
		return isValid;
	}
	
	protected LocalDate localDate;
	protected MonthDay monthDay;
	protected DayOfMonth dayOfMonth;
	protected DayOfWeek dayOfWeek;
	
	protected Period period;
	protected DateValueType mode;
	protected boolean isNormaled = false;
	
	protected void normal() {
		//localDate > monthDay > dayOfMonth > dayOfWeek
		if (this.isNormaled) {
			return;
		}
		this.isNormaled = true;
		if (this.localDate != null) {
			this.monthDay = MonthDay.of(this.localDate.getMonth(), this.localDate.getDayOfMonth());
			this.dayOfMonth = DayOfMonth.of(this.localDate.getDayOfMonth());
			this.dayOfWeek = this.localDate.getDayOfWeek();
			return;
		}
		//localDate = null;
		if (this.monthDay != null) {
			this.dayOfMonth = DayOfMonth.of(this.monthDay.getDayOfMonth());
			this.dayOfWeek = null;
			return;
		}
		//localDate, monthDay == null
		if (this.dayOfMonth != null) {
			this.dayOfWeek = null;
			return;
		}
		//localDate, monthDay, dayOfMonth == null
	}
	
	protected void updateMode() {
		//localDate > monthDay > dayOfMonth > dayOfWeek
		if (this.localDate != null) {
			this.mode = DateValueType.FULL_VALUE;
		} else if(this.monthDay != null) {
			this.mode = DateValueType.MONTH_AND_DAY;
		} else if (this.dayOfMonth != null) {
			this.mode = DateValueType.MONTH_DAY;
		} else if (this.dayOfWeek != null) {
			this.mode = DateValueType.DAY_OF_WEEK;
		} else {
			this.mode = DateValueType.NONE;
		}
	}
}
