package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.StaticInputWordsModel;

public class ValueControlModel extends StaticInputWordsModel<Control> {

	public ValueControlModel() {
		super(AdvanceControlModel.VALUE_CONTROL);
		this.modelName = "VALUE_ADVANCE_CONTROL";
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.VALUE, 0);
	}
}
