package com.bkav.command.model.misc;

import com.bkav.command.data.AmountData;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class AllAmountModel extends StaticInputWordsModel<AmountData> {

	public AllAmountModel() {
		super(ModelData.ALL_AMOUNT);
		this.modelName = "ALL_AMOUNT";
	}

	@Override
	protected AmountData getDataFromStringArray(String[] datas) {
		return AmountData.createFromStringArray(datas, TypeAmount.ALL);
	}
}