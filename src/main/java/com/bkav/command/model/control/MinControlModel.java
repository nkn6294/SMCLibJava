package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class MinControlModel extends StaticInputWordsModel<Control> {

	public MinControlModel() {
		super(ModelData.MIN_CONTROL);
		this.modelName = "MIN_SIMPLE_CONTROL";		
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.MIN, 0);
	}

}
