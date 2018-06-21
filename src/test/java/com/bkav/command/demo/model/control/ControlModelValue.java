package com.bkav.command.demo.model.control;

import com.bkav.command.demo.data.TypeControl;

public class ControlModelValue {
	
	public static ControlModelValue createControlModel(int value, TypeControl typeControl) {
		return new ControlModelValue(value, typeControl);
	}
	
	public int getValue() {
		return this.value;
	}
	public TypeControl getTypeControl() {
		return this.typeControl;
	}
	
	private int value;
	private TypeControl typeControl;
	
	private ControlModelValue(int value, TypeControl typeControl) {
		this.value = value;
		this.typeControl = typeControl;
	}
}
