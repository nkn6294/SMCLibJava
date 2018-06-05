package com.bkav.command.model.entity;

import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.home.data.HomeArea;

public class HomeAreaModel extends StaticInputWordsModel<HomeArea> {

	public HomeAreaModel(String[] datas) {
		super(datas);
		this.modelName = "AREA";
	}
	
	@Override
	protected HomeArea getDataFromStringArray(String[] datas) {
		return HomeArea.createFromStringArray(datas);
	}
}
