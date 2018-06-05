package com.bkav.home.data;

public class Home {
	public Home(String[] areas, String[] functions, String[] devices, String[] deviceTypes) {
		this.areas = areas;
		this.devices = devices;
		this.functions = functions;
		this.deviceTypes = deviceTypes;
	}

	public String[] areas() {
		return this.areas;
	}

	public String[] devices() {
		return this.devices;
	}

	public String[] functions() {
		return this.functions;
	}

	public String[] deviceTypes() {
		return this.deviceTypes;
	}

	private String[] areas;
	private String[] devices;
	private String[] functions;
	private String[] deviceTypes;
}
