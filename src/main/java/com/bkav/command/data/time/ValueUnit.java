package com.bkav.command.data.time;

public class ValueUnit {
	
	// Common UNIT
	public static final String CELSIUS_UNIT_CHAR = "\u2103";
	public static final String PERCENT_UNIT_CHAR = "%";
	public static final String EMPTY_UNIT_CHAR = "";
	
	//AIR UNIT
	public static final String AIR_SPEED = "air_fan";
	public static final String AIR_SWING = "air_swing";
	
	public ValueUnit(int value, String unit) {
		this.value = value;
		this.unit = unit;
	}
	
	public ValueUnit(int value) {
		this(value, EMPTY_UNIT_CHAR);
	}
	
	public int value() {
		return this.value;
	}
	
	public String unit() {
		return this.unit;
	}
	
	@Override
	public String toString() {
		return String.format("%s [value=%s, unit=%s]", this.getClass().getSimpleName(), this.value, this.unit);
	}

	private int value;
	private String unit;
}
