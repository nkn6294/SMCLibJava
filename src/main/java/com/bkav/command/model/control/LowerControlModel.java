package com.bkav.command.model.control;

import java.util.Arrays;

import com.bkav.command.data.Control;
import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.util.CollectionUtil;

public class LowerControlModel extends StaticInputWordsModel<Control> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "LOWER_ADVANCE_CONTROL";
		DATA_PROCESSED = CollectionUtil.convert(AdvanceControlModel.LOWER_CONTROL);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}
	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.LOWER, 0);
	}
}
