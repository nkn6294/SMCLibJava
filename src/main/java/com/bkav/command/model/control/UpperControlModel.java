package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class UpperControlModel extends StaticInputWordsModel<Control> {

	public UpperControlModel() {
		super(ModelData.UPPER_CONTROL);
		this.modelName = "UPPER_ADVANCE_CONTROL";		
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.UPPER, 0);
	}

}
