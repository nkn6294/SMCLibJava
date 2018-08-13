package com.bkav.command.data.time;

import com.bkav.command.data.CommonData;

public class TimeRepeat extends CommonData {
	public enum TimeRepeatType {
		ONCE,
		DAILY,
		WEEKLY,
		MONTHLY,
		YEARLY
	}
	
	public static TimeRepeat of(TimeRepeatType type) {
		return new TimeRepeat("", type);
	}
	
	public static TimeRepeat createFromStringArray(String[] datas, TimeRepeatType timeRepeatType) {
		return new TimeRepeat(getSimpleName(datas), timeRepeatType);
	}

	public TimeRepeat(String name, TimeRepeatType typeControl) {
		super(name);
		this.timeRepeatType = typeControl;
	}
	public TimeRepeat(String name) {
		this(name, TimeRepeatType.ONCE);
	}

	public TimeRepeatType timeRepeatType(TimeRepeatType timeRepeatType) {
		this.timeRepeatType = timeRepeatType;
		return this.timeRepeatType;
	}

	public TimeRepeatType timeRepeatType() {
		return this.timeRepeatType;
	}

	@Override
	public String toString() {
		return String.format("%s [timeRepeatType=%s, name=%s]",
				this.getClass().getSimpleName(),
				timeRepeatType, name);
	}

	private TimeRepeatType timeRepeatType;
}
