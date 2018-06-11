package com.bkav.command.model.misc;

import com.bkav.command.data.AmountData;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class OnlyAmountModel extends StaticInputWordsModel<AmountData> {

	public OnlyAmountModel() {
		super(ModelData.ONLY_AMOUNT);
		this.modelName = "ONLY_AMOUNT";
	}

	@Override
	protected AmountData getDataFromStringArray(String[] datas) {
		return AmountData.createFromStringArray(datas, TypeAmount.ONLY);
	}
}