package com.bkav.home.data;

import com.bkav.command.data.CommonData;

public class HomeArea extends CommonData {

	public static HomeArea createFromStringArray(String[] datas) {
		return new HomeArea(getSimpleName(datas));
	}
	
	public HomeArea(String name) {
		super(name);
	}
	
	
}
