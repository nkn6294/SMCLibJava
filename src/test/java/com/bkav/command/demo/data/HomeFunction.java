package com.bkav.command.demo.data;

import com.bkav.command.data.CommonData;

public class HomeFunction extends CommonData {
	public static HomeFunction createFromStringArray(String[] datas) {
		return new HomeFunction(getSimpleName(datas));
	}

	public HomeFunction(String name) {
		super(name);
	}
}
