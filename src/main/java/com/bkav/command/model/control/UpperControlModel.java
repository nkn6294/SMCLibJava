package com.bkav.command.model.control;

import java.util.Arrays;

import com.bkav.command.data.Control;
import com.bkav.command.model.CommonModel;
import com.bkav.util.CollectionUtil;

public class UpperControlModel extends CommonModel<Control> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "UPPER_ADVANCE_CONTROL";
		DATA_PROCESSED = CollectionUtil.convert(AdvanceControlModel.UPPER_CONTROL);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected Control getDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.UPPER, 0);
	}

}
