package com.bkav.command.model.control;

import java.util.Arrays;

import com.bkav.command.data.Control;
import com.bkav.command.model.CommonModel;
import com.bkav.util.CollectionUtil;

public class MaxControlModel extends CommonModel<Control> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "MAX_SIMPLE_CONTROL";
		DATA_PROCESSED = CollectionUtil.convert(SimpleControlModel.MAX_CONTROL);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected Control createDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.MAX, 0);
	}

}