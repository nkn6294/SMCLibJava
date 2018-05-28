package com.bkav.command.model.entity;

import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.SampleData;
import com.bkav.home.data.HomeArea;

public class HomeAreaModel extends StaticInputWordsModel<HomeArea> {

	public HomeAreaModel() {
		super(SampleData.AREAS);
		this.MODEL_NAME = "AREA";
	}
	
	@Override
	protected HomeArea getDataFromStringArray(String[] datas) {
		return HomeArea.createFromStringArray(datas);
	}
}
