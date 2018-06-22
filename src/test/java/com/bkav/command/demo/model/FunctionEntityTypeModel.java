package com.bkav.command.demo.model;

import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.HomeEntityType;
import com.bkav.command.model.StaticInputWordsModel;

public class FunctionEntityTypeModel extends StaticInputWordsModel<HomeEntityType> {

	public FunctionEntityTypeModel() {
		super(SampleData.FUNCTION_ENTITY_TYPE);
		this.modelName = "FUNCTION_ENTITY_TYPE";		
	}

	@Override
	protected HomeEntityType getDataFromStringArray(String[] datas) {
		return HomeEntityType.createFromStringArray(datas, EntityType.FUNCTION);
	}

}

