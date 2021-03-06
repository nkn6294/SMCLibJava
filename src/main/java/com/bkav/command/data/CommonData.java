package com.bkav.command.data;

public class CommonData {
	
	public CommonData(String name) {
		this.name = name;
		this.id = this.getClass().getSimpleName() + "_" + name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return String.format("%s [id=%s, name=%s]", this.getClass().getSimpleName(), id, name);
	}

	protected String id;
	protected String name;
	
	protected static String getSimpleName(String[] datas) {
		return String.join("_", datas);
	}
}
