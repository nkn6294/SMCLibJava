package com.bkav.command.data;

import com.bkav.command.model.ModelData;
import com.bkav.home.data.Home;

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
