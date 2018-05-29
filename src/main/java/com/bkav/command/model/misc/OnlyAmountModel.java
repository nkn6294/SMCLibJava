package com.bkav.command.model.misc;

import com.bkav.command.data.AmountData;
import com.bkav.command.model.StaticInputWordsModel;

public class OnlyAmountModel extends StaticInputWordsModel<AmountData> {

	public OnlyAmountModel() {
		super(AmountModel.ONLY_AMOUNT);
		this.MODEL_NAME = "ONLY_AMOUNT";
	}

	@Override
	protected AmountData getDataFromStringArray(String[] datas) {
		return AmountData.createFromStringArray(datas, TypeAmount.ONLY);
	}
}