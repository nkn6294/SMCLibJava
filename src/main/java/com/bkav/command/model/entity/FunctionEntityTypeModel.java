package com.bkav.command.model.entity;

import com.bkav.command.data.HomeEntityType;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class FunctionEntityTypeModel extends StaticInputWordsModel<HomeEntityType> {

	public FunctionEntityTypeModel() {
		super(ModelData.FUNCTION_ENTITY_TYPE);
		this.modelName = "FUNCTION_ENTITY_TYPE";		
	}

	@Override
	protected HomeEntityType getDataFromStringArray(String[] datas) {
		return HomeEntityType.createFromStringArray(datas, EntityType.FUNCTION);
	}

}

