package com.bkav.command.data.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.temporal.TemporalAmount;

import com.bkav.command.data.Context;
import com.bkav.command.data.time.TimeRepeat.TimeRepeatType;
import com.bkav.command.data.time.TimeValue.DayContext;

public class ScheduleObject {
	public enum ScheduleUnitDefaultMode {
		NONE, NOW, START
	};

	public static ScheduleInformation process(ScheduleObject scheduleObject,
			ScheduleUnitDefaultMode scheduleUnitDefaultMode, TemporalAmount duration) throws Exception {
		if (scheduleObject == null) {
			throw new Exception("Schedule invalid: " + scheduleObject);
		}
		if (!scheduleObject.isValid()) {
			throw new Exception("Schedule invalid: " + scheduleObject);
		}
		LocalDateTime now = LocalDateTime.now();
		TimeRepeat scheduleTimeRepeat = scheduleObject.timeRepeat();
		LocalDateTime scheduleDateTime = scheduleObject.dateTime();
		LocalDate scheduleDate = scheduleObject.date();
		LocalTime scheduleTime = scheduleObject.time();
		DayOfWeek dayOfWeek = scheduleObject.dayOfWeek();
		MonthDay monthDay = scheduleObject.monthDay();
		DayOfMonth dayOfMonth = scheduleObject.dayOfMonth();
		DayContext dayContext = scheduleObject.dayContext();
		if (scheduleTimeRepeat == null) {
			scheduleTimeRepeat = TimeRepeat.of(TimeRepeatType.ONCE);
		}
		if (scheduleDateTime == null) {
			if (scheduleUnitDefaultMode == ScheduleUnitDefaultMode.NONE) {
			} else if (scheduleUnitDefaultMode == ScheduleUnitDefaultMode.START) {
				if (scheduleTime == null) {
					scheduleTime = LocalTime.of(0, 0);
				}
			} else if (scheduleUnitDefaultMode == ScheduleUnitDefaultMode.NOW) {
				if (scheduleDate == null) {
					scheduleDate = now.toLocalDate();
				}
				if (scheduleTime == null) {
					scheduleTime = now.toLocalTime();
				}
			}
			if (scheduleDate != null && scheduleTime != null) {
				scheduleDateTime = LocalDateTime.of(scheduleDate, scheduleTime);
			}
		}
		if (scheduleTimeRepeat.timeRepeatType() == TimeRepeatType.ONCE) {
			if (scheduleDateTime == null) {
				throw new Exception(String.format("Not enough information[scheduleDateTime]. %s. %s", scheduleObject,
						scheduleUnitDefaultMode));
			}
		} else if (scheduleTimeRepeat.timeRepeatType() == TimeRepeatType.DAILY) {
			if (scheduleTime == null) {
				throw new Exception(String.format("Not enough information[scheduleTime]. %s. %s", scheduleObject,
						scheduleUnitDefaultMode));
			}
		} else if (scheduleTimeRepeat.timeRepeatType() == TimeRepeatType.WEEKLY) { // thu x hang tuan
			if (dayOfWeek == null) {
				throw new Exception(String.format("Not enough information[dayOfWeek]. %s. %s", scheduleObject,
						scheduleUnitDefaultMode));
			}
		} else if (scheduleTimeRepeat.timeRepeatType() == TimeRepeatType.MONTHLY) { 
			if (dayOfMonth == null) {
				throw new Exception(String.format("Not enough information[dayOfMonth]. %s. %s", scheduleObject,
						scheduleUnitDefaultMode));
			}
		} else if (scheduleTimeRepeat.timeRepeatType() == TimeRepeatType.YEARLY) { 
			if (monthDay == null) {
				throw new Exception(String.format("Not enough information[monthDay]. %s. %s", scheduleObject,
						scheduleUnitDefaultMode));
			}
		}
		if (scheduleDateTime == null) {
			scheduleDateTime = now;
		}
		LocalDateTime beginDateTime = scheduleDateTime;
		TimeRepeatType currentRepeat = scheduleTimeRepeat.timeRepeatType();
		if (beginDateTime.isBefore(now)) {
			switch (currentRepeat) {
			case ONCE: {
				LocalDateTime tempDateTime = beginDateTime;
				int timeHour = tempDateTime.getHour();
				if (timeHour < 12 && dayContext != DayContext.AM) {
					tempDateTime = tempDateTime.plusHours(12);
				}
				if (tempDateTime.isBefore(now)) {
					beginDateTime = beginDateTime.plusDays(1);
				} else {
					beginDateTime = tempDateTime;
				}
				if (beginDateTime.isBefore(now)) {
					throw new Exception("Date schedule must be after now");
				}
				break;
			}
			case DAILY:
				beginDateTime = beginDateTime.plusDays(1);
				break;
			case WEEKLY:
				beginDateTime = beginDateTime.plusWeeks(1);
				break;
			case MONTHLY:
				beginDateTime = beginDateTime.plusMonths(1);
				break;
			case YEARLY:
				beginDateTime = beginDateTime.plusYears(1);
				break;
			default:
				break;
			}
		}
		return new ScheduleInformation(beginDateTime, currentRepeat, duration);
	}

	public ScheduleObject(TimeValue timeValue, DateValue dateValue, TimeRepeat timeRepeat, Context context) {
		this.dateValue = dateValue;
		this.timeValue = timeValue;
		this.timeRepeat = timeRepeat;
		this.context = context;
		this.normal();
	}

	public DateValue dateValue() {
		return this.dateValue;
	}

	public TimeValue timeValue() {
		return this.timeValue;
	}

	public DayContext dayContext() {
		return this.timeValue.dayContext();
	}

	public LocalDate date() {
		LocalDate date = this.dateValue.date();
		return date == null ? this.date : date;
	}

	public LocalTime time() {
		LocalTime time = this.timeValue.time();
		return time == null ? this.time : time;
	}

	public LocalDateTime dateTime() {
		LocalDate date = this.date();
		LocalTime time = this.time();
		if (date != null && time != null) {
			return LocalDateTime.of(date, time);
		}
		return this.dateTime;
	}

	public MonthDay monthDay() {
		MonthDay monthDay = this.dateValue.monthDay();
		return monthDay == null ? this.monthDay : monthDay;
	}

	public DayOfMonth dayOfMonth() {
		DayOfMonth dayOfMonth = this.dateValue.dayOfMonth();
		return dayOfMonth == null ? this.dayOfMonth : dayOfMonth;
	}

	public DayOfWeek dayOfWeek() {
		DayOfWeek dayOfWeek = this.dateValue.dayOfWeek();
		return dayOfWeek == null ? this.dayOfWeek : dayOfWeek;
	}

	public TimeRepeat timeRepeat() {
		if (this.timeRepeat == null) {
			this.timeRepeat = TimeRepeat.of(TimeRepeatType.ONCE);
		}
		return this.timeRepeat;
	}

	/***
	 * Normal unit value, set status {@link ScheduleObject#isValid()} to false if
	 * conflic or not enought information.
	 */
	public void normal() {
		if (this.isNormaled) {
			return;
		}
		this.isNormaled = true;
		this.checkValid();
		if (!this.isValid()) {
			return;
		}
		this.timeValue.normal();
		this.dateValue.normal();
		if (this.timeRepeat == null) {
			// DEFAUL ONCE if not info
			this.timeRepeat = TimeRepeat.of(TimeRepeatType.ONCE);
		}
		if (this.timeValue.isValid() && this.dateValue.isValid()) {
			// full datetime -> ONCE, DAILY, WEEKLY, MONTHLY, YEARLY
		} else if (this.dateValue.isValid()) {
			// only date -> default time (now or 0,0) -> ONCE, DAILY, WEEKLY, MONTHLY,
			// YEARLY
		} else if (this.timeValue.isValid()) {// only time -> ONCE, DAILY
			if (this.timeRepeat.timeRepeatType() != TimeRepeatType.ONCE
					&& this.timeRepeat.timeRepeatType() != TimeRepeatType.DAILY) {
				this.isValid = false;
			}
		} else {
			// without datetime -> default time (now, 0, 0) -> DAILY
			if (this.timeRepeat.timeRepeatType() != TimeRepeatType.DAILY) {
				this.isValid = false;
			}
		}
	}

	/***
	 * Normal with now.
	 */
	public boolean normalNow() throws Exception {
		this.normal();
		LocalDateTime now = LocalDateTime.now();
		if (this.dateTime() != null) {
			if (this.dateTime().isBefore(now)) {
				throw new Exception("Date schedule must be after now. ScheduleDateTime: " + this.dateTime());
			}
		}
		return true;
	}

	public boolean isValid() {
		return this.isValid;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [dateValue=" + dateValue + ", timeValue=" + timeValue
				+ ", timeRepeat=" + timeRepeat + ", dateTime=" + this.dateTime() + "]";
	}

	protected DateValue dateValue;
	protected TimeValue timeValue;
	protected TimeRepeat timeRepeat;
	protected Context context;
	protected boolean isNormaled = false;

	private boolean isValid = false;
	private LocalDateTime dateTime = null;
	private LocalTime time = null;
	private LocalDate date = null;
	private MonthDay monthDay = null;
	private DayOfMonth dayOfMonth = null;
	private DayOfWeek dayOfWeek = null;

	private void checkValid() {
		this.isValid = this.dateValue.isValid() || this.timeValue.isValid() || this.timeRepeat != null;
	}
}