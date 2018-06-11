package com.bkav.command.model.misc;

import com.bkav.command.data.AmountData;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class SomeAmountModel extends StaticInputWordsModel<AmountData> {

	public SomeAmountModel() {
		super(ModelData.SOME_AMOUNT);
		this.modelName = "SOME_AMOUNT";
	}

	@Override
	protected AmountData getDataFromStringArray(String[] datas) {
		return AmountData.createFromStringArray(datas, TypeAmount.SOME);
	}
}