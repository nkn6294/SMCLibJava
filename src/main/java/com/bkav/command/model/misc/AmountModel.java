package com.bkav.command.model.misc;

import com.bkav.command.model.CollectionModel;

public class AmountModel extends CollectionModel {

	public static final String[] ALL_AMOUNT = {
			"tat ca",
			"toan bo",
		};
	public static final String[] SOME_AMOUNT = {
		"mot vai",	
	};
	
	public static final String[] ONLY_AMOUNT = {
		"duy nhat",
		"chi"
	};
	
	@Override
	protected void init() {
		super.init();
		this.modelName = "CONTROL";
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