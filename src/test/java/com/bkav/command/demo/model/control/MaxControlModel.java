package com.bkav.command.demo.model.control;

import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.Control;
import com.bkav.command.demo.data.TypeControl;
import com.bkav.command.model.StaticInputWordsModel;

public class MaxControlModel extends StaticInputWordsModel<Control> {

	public MaxControlModel() {
		super(SampleData.MAX_CONTROL);
		this.modelName = "MAX_SIMPLE_CONTROL";		
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.MAX, 0);
	}

}
