package com.bkav.command.demo.model.control;

import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.Control;
import com.bkav.command.demo.data.TypeControl;
import com.bkav.command.model.StaticInputWordsModel;

public class MinControlModel extends StaticInputWordsModel<Control> {

	public MinControlModel() {
		super(SampleData.MIN_CONTROL);
		this.modelName = "MIN_SIMPLE_CONTROL";		
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.MIN, 0);
	}

}
