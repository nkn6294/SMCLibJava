package com.bkav.command.demo.data;

import com.bkav.command.data.SampleData;
import com.bkav.command.model.ModelData;

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
				ModelData.DEVICE_TYPE);
	}
}
