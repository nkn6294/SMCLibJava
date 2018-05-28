package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.StaticInputWordsModel;

public class LowerControlModel extends StaticInputWordsModel<Control> {

	public LowerControlModel() {
		super(AdvanceControlModel.LOWER_CONTROL);
		this.MODEL_NAME = "LOWER_ADVANCE_CONTROL";
	}
	
	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.LOWER, 0);
	}
}
