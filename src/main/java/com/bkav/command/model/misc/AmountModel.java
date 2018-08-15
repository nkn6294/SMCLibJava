package com.bkav.command.model.misc;

import com.bkav.command.data.ModelData;
import com.bkav.command.data.misc.AmountData;
import com.bkav.command.data.misc.TypeAmount;
import com.bkav.command.model.common.CollectionModel;
import com.bkav.command.model.common.StaticInputWordsModel;

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
	
	class AllAmountModel extends StaticInputWordsModel<AmountData> {

		public AllAmountModel() {
			super(ModelData.ALL_AMOUNT);
			this.modelName = "ALL_AMOUNT";
		}

		@Override
		protected AmountData getDataFromStringArray(String[] datas) {
			return AmountData.createFromStringArray(datas, TypeAmount.ALL);
		}
	}
	
	class SomeAmountModel extends StaticInputWordsModel<AmountData> {

		public SomeAmountModel() {
			super(ModelData.SOME_AMOUNT);
			this.modelName = "SOME_AMOUNT";
		}

		@Override
		protected AmountData getDataFromStringArray(String[] datas) {
			return AmountData.createFromStringArray(datas, TypeAmount.SOME);
		}
	}
	
	class OnlyAmountModel extends StaticInputWordsModel<AmountData> {

		public OnlyAmountModel() {
			super(ModelData.ONLY_AMOUNT);
			this.modelName = "ONLY_AMOUNT";
		}

		@Override
		protected AmountData getDataFromStringArray(String[] datas) {
			return AmountData.createFromStringArray(datas, TypeAmount.ONLY);
		}
	}
}