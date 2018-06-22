package com.bkav.command.demo.model.control;

import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.Control;
import com.bkav.command.demo.data.TypeControl;
import com.bkav.command.model.StaticInputWordsModel;

public class LowerControlModel extends StaticInputWordsModel<Control> {

	public LowerControlModel() {
		super(SampleData.LOWER_CONTROL);
		this.modelName = "LOWER_ADVANCE_CONTROL";
	}
	
	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.LOWER, 0);
	}
}
