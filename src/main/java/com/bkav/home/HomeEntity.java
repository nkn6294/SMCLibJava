package com.bkav.home;

import com.bkav.command.data.CommonData;

public class HomeEntity extends CommonData {
	public static HomeEntity createFromStringArray(String[] datas) {
		return new HomeEntity(getSimpleName(datas));
	}

	public HomeEntity(String name) {
		super(name);
	}

}
