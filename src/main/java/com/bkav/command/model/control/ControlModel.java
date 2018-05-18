package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.CollectionModel;

public class ControlModel extends CollectionModel<Control> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(new SimpleControlModel(), new AdvanceControlModel());
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.NONE, 0);
	}
}
