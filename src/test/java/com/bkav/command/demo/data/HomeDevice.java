package com.bkav.command.demo.data;

import com.bkav.command.data.CommonData;

public class HomeDevice extends CommonData {
	
	public static HomeDevice createFromStringArray(String[] datas) {
		return new HomeDevice(getSimpleName(datas));
	}
	
	public HomeDevice(String name) {
		super(name);
		this.minValue = 0;
		this.maxValue = 100;
	}
	
	public void turnOn() {
		this.value = this.maxValue;
	}
	
	public void turnOff() {
		this.value = this.minValue;
	}
	
	public void setMin() {
		this.value = this.minValue;
	}
	
	public void setMax() {
		this.value = this.maxValue;
	}
	
	public boolean increase(int value) {
		return this.setValue(this.value + value);
	}
	
	public boolean decrease(int value) {
		return this.setValue(this.value - value);
	}
	
	public int getValue() {
		return this.value;
	}
	
	public boolean setValue(int value) {
		if (value >= this.minValue && value <= this.maxValue) {
			this.value = value;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%s [id=%s, name=%s, value=%s]", this.getClass().getSimpleName(), id, name, value);
	}
	
	private int value;
	
	private int maxValue = 100;
	private int minValue = 0;
}
