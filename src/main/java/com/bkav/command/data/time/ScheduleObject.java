package com.bkav.command.data.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.bkav.command.data.Context;
import com.bkav.command.data.time.TimeRepeat;
import com.bkav.command.data.time.TimeRepeat.TimeRepeatType;

public class ScheduleObject {

	public ScheduleObject(TimeValue time, LocalDate date, LocalDateTime dateTime, DayOfWeek dayOfWeek,
			TimeRepeat timeRepeat, Context context) {
		this.date = date;
		this.time = time;
		this.dayOfWeek = dayOfWeek;
		this.timeRepeat = timeRepeat;
		this.dateTime = dateTime;
		this.context = context;
	}

	public LocalDate date() {
		return this.date;
	}

	public LocalTime time() {
		return this.time.time();
	}

	public LocalDateTime dateTime() {
		return this.dateTime;
	}

	public DayOfWeek dayOfWeek() {
		return this.dayOfWeek;
	}

	public TimeRepeat timeRepeat() {
		return this.timeRepeat;
	}

	public void normal() {
		if (this.isNormaled) {
			return;
		}
		//order normal: dateTime > date > time > dayOfWeek > timeRepeat
		if (this.dateTime != null) {
			this.date = null;
			this.time = null;
			this.dayOfWeek = null;
			if (this.timeRepeat == null) {
				this.timeRepeat = new TimeRepeat("", TimeRepeatType.ONCE);				
			} else {
				this.timeRepeat.timeRepeatType(TimeRepeatType.ONCE);
			}
			return;
		} 
		//dateTime = null
		if (this.date != null) {
			if (this.time == null) {
				this.time = new TimeValue(LocalTime.of(0, 0));
			}
			if (dayOfWeek == null) {
				dayOfWeek = this.date.getDayOfWeek();
			}
			if (this.timeRepeat == null) {
				this.timeRepeat = new TimeRepeat("", TimeRepeatType.ONCE);
			}
			return;
		}
		//[dateTime, date] = null
		if (this.time != null) {
			if (this.timeRepeat == null) {
				this.timeRepeat = new TimeRepeat("", TimeRepeatType.DAILY);
			} else {
//				this.timeRepeat.timeRepeatType(TimeRepeatType.DAILY);
			}
			if (dayOfWeek == null) {
				dayOfWeek = context.startOfWeek();
			}
			return;
		}
		//[dateTime, date, time] = null
		if (this.dayOfWeek != null) {
			if (this.timeRepeat == null) {
				this.timeRepeat = new TimeRepeat("", TimeRepeatType.WEEKLY);
			} else {
				//ERROR
			}
			return;
		}
		//[dateTime, date, time, dayOfWeek] = null
		if (this.timeRepeat != null) {
			this.dayOfWeek = context.startOfWeek();
			switch (this.timeRepeat.timeRepeatType()) {
			case ONCE:
				break;
			case DAILY:
				break;
			case WEEKLY:
				break;
			case MONTHLY:
				break;
			case YEARLY:
				break;
			default: 
				break;
			}
		}
	}
	/***
	 * Normal with now.
	 */
	public boolean normalNow() throws Exception {
		this.normal();
		LocalDateTime now = LocalDateTime.now();
		if (this.dateTime != null) {
			if (this.dateTime.isBefore(now)) {
				throw new Exception("Date schedule must be after now. ScheduleDateTime: " + this.dateTime);
			}
		}
		return true;
	}
	
	public boolean isValid() {
		//TODO complete isValis ScheduleObject
		boolean isValid = true;
		isValid = this.date != null 
				|| this.time != null 
				|| this.dateTime != null
				|| this.dayOfWeek != null
				|| this.timeRepeat != null;
//		if (this.time == null && this.date == null && this.dateTime == null) {
//			isValid = false;
//		}
//		else if (dayOfWeek == null && this.timeRepeat == null) {
//			isValid = false;
//		}
		return isValid;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [date=" + date + ", time=" + time + ", dayOfWeek=" + dayOfWeek
				+ ", timeRepeat=" + timeRepeat + ", dateTime=" + dateTime + "]";
	}
	
	protected LocalDate date;
	protected TimeValue time;
	protected DayOfWeek dayOfWeek;
	protected TimeRepeat timeRepeat;
	protected LocalDateTime dateTime;
	protected Context context;
	protected boolean isNormaled = false;
}