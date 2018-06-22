package com.bkav.command.demo.model.control;

import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.Control;
import com.bkav.command.demo.data.TypeControl;
import com.bkav.command.model.StaticInputWordsModel;

public class ValueControlModel extends StaticInputWordsModel<Control> {

	public ValueControlModel() {
		super(SampleData.VALUE_CONTROL);
		this.modelName = "VALUE_ADVANCE_CONTROL";
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.VALUE, 0);
	}
}
