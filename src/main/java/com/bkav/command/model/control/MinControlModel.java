package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.StaticInputWordsModel;

public class MinControlModel extends StaticInputWordsModel<Control> {

	public MinControlModel() {
		super(SimpleControlModel.MIN_CONTROL);
		this.MODEL_NAME = "MIN_SIMPLE_CONTROL";		
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.MIN, 0);
	}

}
