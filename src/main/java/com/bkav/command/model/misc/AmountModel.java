package com.bkav.command.model.misc;

import com.bkav.command.model.CollectionModel;

public class AmountModel extends CollectionModel {

	public final static String[] ALL_AMOUNT = {
			"tat ca",
			"toan bo",
		};
	public final static String[] SOME_AMOUNT = {
		"mot vai",	
	};
	
	public final static String[] ONLY_AMOUNT = {
		"duy nhat",
		"chi"
	};
	
	@Override
	protected void init() {
		super.init();
		this.MODEL_NAME = "CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new AllAmountModel(),
				new SomeAmountModel(),
				new OnlyAmountModel()
				);
	}
}