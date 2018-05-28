package com.bkav.command.model.entity;

import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.SampleData;
import com.bkav.home.data.HomeFunction;

public class HomeFunctionModel extends StaticInputWordsModel<HomeFunction> {

	public HomeFunctionModel() {
		super(SampleData.FUNCTION);
		this.MODEL_NAME = "FUNCTION";
	}
	
	@Override
	protected HomeFunction getDataFromStringArray(String[] datas) {
		return HomeFunction.createFromStringArray(datas);
	}

}
