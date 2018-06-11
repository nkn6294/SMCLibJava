package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class MaxControlModel extends StaticInputWordsModel<Control> {

	public MaxControlModel() {
		super(ModelData.MAX_CONTROL);
		this.modelName = "MAX_SIMPLE_CONTROL";		
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.MAX, 0);
	}

}
