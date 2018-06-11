package com.bkav.command.model.misc;

import com.bkav.command.model.CollectionModel;

public class AmountModel extends CollectionModel {

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