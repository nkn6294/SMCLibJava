package com.bkav.command.model.control;

import com.bkav.command.model.CollectionModel;

public class AdvanceControlModel extends CollectionModel {
	public static final String[] LOWER_CONTROL = { 
			"giam", 
			"bot", };

	public static final  String[] UPPER_CONTROL = { 
			"tang", 
			"them", 
			"lon hon", };

	public static final String[] VALUE_CONTROL = {
			"dat",
			"dieu chinh",
			"thay doi"
	};

	@Override
	protected void init() {
		super.init();
		this.modelName = "ADVANCE_CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(new LowerControlModel(), 
				new UpperControlModel(), 
				new ValueControlModel());
	}
}
