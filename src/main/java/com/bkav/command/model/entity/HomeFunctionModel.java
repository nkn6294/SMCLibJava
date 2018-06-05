package com.bkav.command.model.entity;

import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.home.data.HomeFunction;

public class HomeFunctionModel extends StaticInputWordsModel<HomeFunction> {

	public HomeFunctionModel(String[] datas) {
		super(datas);
		this.modelName = "FUNCTION";
	}
	
	@Override
	protected HomeFunction getDataFromStringArray(String[] datas) {
		return HomeFunction.createFromStringArray(datas);
	}

}
