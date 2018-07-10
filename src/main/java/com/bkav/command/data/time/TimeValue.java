package com.bkav.command.data.time;

import java.time.Duration;
import java.time.LocalTime;

public class TimeValue {
	public enum TimeValueModel {
		VALUE, 
		CONTEXT,
	}
	
	public TimeValue(LocalTime localTime, Duration period) {
		this.localTime = localTime;
		this.duration = period;
		this.updateMode();
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
		if (this.localTime == null) {
			LocalTime now = LocalTime.now();
			output = now;//LocalTime.of(now.getHour(), now.getMinute());
		} else {
			output = LocalTime.of(this.localTime.getHour(), this.localTime.getMinute());
		}
		if (this.duration != null) {
			output = output.plus(duration);
		}
		return output;
	}
	
	public TimeValueModel mode() {
		return mode;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + 
				" [localTime=" + localTime + ", duration=" + duration + ", mode=" + mode + "]";
	}

	protected LocalTime localTime;
	protected Duration duration;
	protected TimeValueModel mode;
	
	protected final void updateMode() {
		if (duration == null) {
			this.mode = TimeValueModel.VALUE;
		} else {
			this.mode = TimeValueModel.CONTEXT;
		}
	}
}
