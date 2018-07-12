package com.bkav.command.data.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import com.bkav.command.data.time.TimeRepeat.TimeRepeatType;

public class ScheduleInformation {

	public static final TemporalAmount DEFAULT_TEMPORAL_AMOUNT = Duration.ofHours(1);
	public static final TimeRepeatType DEFAULT_TIME_REPEAT = TimeRepeatType.ONCE;
	
	public ScheduleInformation(LocalDateTime beginTime, LocalDateTime endTime, TimeRepeatType repeat) {
		this.repeat = repeat;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.checkValid();
	}

	public ScheduleInformation(LocalDateTime beginTime, TimeRepeatType repeat, TemporalAmount plus) {
		this.repeat = repeat;
		this.beginTime = beginTime;
		this.endTime = this.beginTime;
		if (plus != null) {
			this.endTime = this.endTime.plus(plus);
		}
		this.checkValid();
	}
	public ScheduleInformation(LocalDateTime beginTime, LocalDateTime endTime) {
		this(beginTime, endTime, DEFAULT_TIME_REPEAT);
	}
	
	public ScheduleInformation(LocalDateTime beginTime, TimeRepeatType repeat) {
		this(beginTime, repeat, DEFAULT_TEMPORAL_AMOUNT);
	}

	public ScheduleInformation(LocalDateTime beginTime, TemporalAmount plus) {
		this(beginTime, DEFAULT_TIME_REPEAT, plus);
	}
	public ScheduleInformation(LocalDateTime beginTime) {
		this(beginTime, TimeRepeatType.ONCE, DEFAULT_TEMPORAL_AMOUNT);
	}
	public LocalDateTime beginTime() {
		return beginTime;
	}

	public LocalDateTime endTime() {
		return endTime;
	}

	public TimeRepeatType repeat() {
		return repeat;
	}

	public boolean isValid() {
		return this.isValid;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [beginTime=" + beginTime + ", endTime=" + endTime + ", repeat=" + repeat
				+ ", isValid=" + isValid + "]";
	}

	private LocalDateTime beginTime;
	private LocalDateTime endTime;
	private TimeRepeatType repeat;
	private boolean isValid = false;
	
	protected void checkValid() {
		this.isValid = this.beginTime.isBefore(this.endTime);
	}
}
