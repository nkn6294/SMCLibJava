package com.bkav.command.data.time;

import java.time.Duration;
import java.time.LocalTime;

public class TimeValue {
	public enum TimeValueModel {
		VALUE, 
		CONTEXT,
	}
	public enum DayContext {
		AM, PM, AM_PM
	}
	public TimeValue(LocalTime localTime, Duration period, DayContext dayContext) {
		this.localTime = localTime;
		this.duration = period;
		this.dayContext = dayContext;
		this.normal();
		this.updateMode();
	}
	public TimeValue(LocalTime localTime, Duration period) {
		this(localTime, period, DayContext.AM_PM);
	}
	public TimeValue(LocalTime localTime) {
		this(localTime, null);
	}
	
	public TimeValue(Duration period) {
		this(null, period);
	}
	
	public TimeValue() {
		this(null, null);
	}
	
	public LocalTime localTime() {
		return this.localTime;
	}
	
	public Duration duration() {
		return this.duration;
	}
	/***
	 * Get time with context {@link Duration}
	 * @return {@linkplain LocalTime time} with context
	 */
	public LocalTime time() {
		LocalTime output = null;
		if (this.localTime != null) {
//			output = this.localTime;//LocalTime.of(this.localTime.getHour(), this.localTime.getMinute());
			return this.localTime;
		} else if (this.duration != null) {
			output = LocalTime.now().plus(duration);				
		} else {
//			LocalTime now = LocalTime.now();
//			output = now;//LocalTime.of(now.getHour(), now.getMinute());
		}
		return output;
	}
	
	public TimeValueModel mode() {
		return mode;
	}
	public DayContext dayContext() {
		return this.dayContext;
	}
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [localTime=" + localTime + ", duration=" + duration + ", mode=" + mode + ", isNormaled="
				+ isNormaled + ", isValid=" + isValid + "]";
	}

	public boolean isValid() {
		return this.isValid;
	}
	
	public void normal() {
		if (this.isNormaled) {
			return;
		}
		this.isNormaled = true;
		this.checkValid();
		if (!this.isValid()) {
			return;
		}
		if (this.localTime != null) {
			this.duration = null;
		}
	}
	
	protected LocalTime localTime;
	protected Duration duration;
	protected TimeValueModel mode;
	protected boolean isNormaled = false;
	protected boolean isValid = false;
	protected DayContext dayContext = DayContext.AM_PM;
	
	protected void checkValid() {
		this.isValid = this.localTime != null || this.duration != null;
	}
	
	protected final void updateMode() {
		if (duration == null) {
			this.mode = TimeValueModel.VALUE;
		} else {
			this.mode = TimeValueModel.CONTEXT;
		}
	}
}
