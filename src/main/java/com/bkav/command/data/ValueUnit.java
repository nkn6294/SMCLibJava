package com.bkav.command.data;

public class ValueUnit {
	
	public static final String CELSIUS_UNIT_CHAR = "\u2103";
	public static final String PERCENT_UNIT_CHAR = "%";
	
	public ValueUnit(int value, String unit) {
		this.value = value;
		this.unit = unit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	@Override
	public String toString() {
		return String.format("%s [value=%s, unit=%s]", this.getClass().getSimpleName(), this.value, this.unit);
	}

	private int value;
	private String unit;
}
