package com.bkav.command.demo.model;

import com.bkav.command.demo.data.HomeFunction;
import com.bkav.command.model.StaticInputWordsModel;

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
