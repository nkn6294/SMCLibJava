package com.bkav.command.demo.model;

import com.bkav.command.demo.data.HomeArea;
import com.bkav.command.model.StaticInputWordsModel;

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
