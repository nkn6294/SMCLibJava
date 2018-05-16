package com.bkav.home.data;

import com.bkav.command.data.CommonData;

public class HomeDeviceType extends CommonData {
	public static HomeDeviceType createFromStringArray(String[] datas) {
		return new HomeDeviceType(getSimpleName(datas));
	}

	public HomeDeviceType(String name) {
		super(name);
	}
}
