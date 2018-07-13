package com.bkav.command.data.time;

import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Period;

public class TimeValue {
	public enum TimeValueModel {
		VALUE, 
		CONTEXT,
	}
	public enum DayContext {
		AM, PM, AM_PM
	}
	public TimeValue(LocalTime localTime, Duration duration, DayContext dayContext) {
		this.localTime = localTime;
		this.period = duration == null ? null : duration.toPeriod();
		this.dayContext = dayContext;
		this.normal();
		this.updateMode();
	}
	public TimeValue(LocalTime localTime, Duration duration) {
		this(localTime, duration, DayContext.AM_PM);
	}
	public TimeValue(LocalTime localTime) {
		this(localTime, null);
	}
	
	public TimeValue(Duration duration) {
		this(null, duration);
	}
	
	public TimeValue() {
		this(null, null);
	}
	
	public LocalTime localTime() {
		return this.localTime;
	}
	
	public Period period() {
		return this.period;
	}
	/***
	 * Get time with context {@link Duration}
	 * @return {@linkplain LocalTime time} with context
	 */
	public LocalTime time() {
		LocalTime output = null;
		if (this.localTime != null) {
			return this.localTime;
		} else if (this.period != null) {
			output = LocalTime.now().plus(period.toPeriod());				
		} else {
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
		return this.getClass().getSimpleName() + " [localTime=" + localTime + ", period=" + period + ", mode=" + mode + ", isNormaled="
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
			this.period = null;
		}
	}
	
	protected LocalTime localTime;
	protected Period period;
	protected TimeValueModel mode;
	protected boolean isNormaled = false;
	protected boolean isValid = false;
	protected DayContext dayContext = DayContext.AM_PM;
	
	protected void checkValid() {
		this.isValid = this.localTime != null || this.period != null;
	}
	
	protected final void updateMode() {
		if (period == null) {
			this.mode = TimeValueModel.VALUE;
		} else {
			this.mode = TimeValueModel.CONTEXT;
		}
	}
}
