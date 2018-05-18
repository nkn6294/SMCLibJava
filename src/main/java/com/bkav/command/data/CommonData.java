package com.bkav.command.data;

public class CommonData {
	public static String getSimpleName(String[] datas) {
		return String.join("_", datas);//Arrays.stream(datas).reduce((result, element) -> result += "_" + element).orElse("NULL");
	}
	
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
}
