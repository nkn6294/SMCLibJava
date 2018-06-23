package com.bkav.command.data;


public class TimeRepeat extends CommonData {
	public static TimeRepeat createFromStringArray(String[] datas, TimeRepeatType typeControl) {
		return new TimeRepeat(getSimpleName(datas), typeControl);
	}

	public TimeRepeat(String name, TimeRepeatType typeControl) {
		super(name);
		this.timeRepeatType = typeControl;
	}
	public TimeRepeat(String name) {
		this(name, TimeRepeatType.ONCE);
	}


	public TimeRepeatType setTimeRepeatType(TimeRepeatType timeRepeatType) {
		this.timeRepeatType = timeRepeatType;
		return this.timeRepeatType;
	}


	public TimeRepeatType getTimeRepeatType() {
		return this.timeRepeatType;
	}

	@Override
	public String toString() {
		return String.format("%s [typeControl=%s, name=%s]",
				this.getClass().getSimpleName(),
				timeRepeatType, name);
	}

	private TimeRepeatType timeRepeatType;
}
