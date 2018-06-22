package com.bkav.command.demo.model.control;

import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.Control;
import com.bkav.command.demo.data.TypeControl;
import com.bkav.command.model.StaticInputWordsModel;

public class UpperControlModel extends StaticInputWordsModel<Control> {

	public UpperControlModel() {
		super(SampleData.UPPER_CONTROL);
		this.modelName = "UPPER_ADVANCE_CONTROL";		
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.UPPER, 0);
	}
}
