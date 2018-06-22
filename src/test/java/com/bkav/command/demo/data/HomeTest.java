package com.bkav.command.demo.data;

import com.bkav.command.demo.SampleData;

public class HomeTest extends Home {

	public static Home getHomeTest() {
		if (homeTest == null) {
			homeTest = new HomeTest();
		}
		return homeTest;
	}
	private static Home homeTest;

	private HomeTest() {
		super(SampleData.AREAS, 
				SampleData.FUNCTION, 
				SampleData.DEVICES, 
				SampleData.DEVICE_TYPE);
	}
}
