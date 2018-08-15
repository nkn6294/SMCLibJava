package com.bkav.command.common;

public class StringWithTypeData extends CommonData {

	public StringWithTypeData(String type, String value) {
		super("");
		this.value = value;
		this.type = type;
	}
	public StringWithTypeData(String value) {
		super("");
		this.value = value;
		this.type = "";
	}
	public String value() {
		return this.value;
	}

	public void value(String value) {
		this.value = value;
	}
	
	public void type(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	protected String value;
	protected String type;
}