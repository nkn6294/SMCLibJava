package com.bkav.command.model.control;

import com.bkav.command.model.CollectionModel;

public class AdvanceControlModel extends CollectionModel {
	public final static String[] LOWER_CONTROL = { 
			"giam", 
			"bot", };

	public final static String[] UPPER_CONTROL = { 
			"tang", 
			"them", 
			"lon hon", };

	public final static String[] VALUE_CONTROL = {
			"dat",
			"dieu chinh",
			"thay doi"
	};

	@Override
	protected void init() {
		super.init();
		this.MODEL_NAME = "ADVANCE_CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(new LowerControlModel(), 
				new UpperControlModel(), 
				new ValueControlModel());
	}
}
