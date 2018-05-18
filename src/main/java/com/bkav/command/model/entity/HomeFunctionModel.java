package com.bkav.command.model.entity;

import java.util.Arrays;

import com.bkav.command.model.CommonModel;
import com.bkav.command.test.SampleData;
import com.bkav.home.data.HomeFunction;
import com.bkav.util.CollectionUtil;

public class HomeFunctionModel extends CommonModel<HomeFunction> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "FUNCTION";
		DATA_PROCESSED = CollectionUtil.convert(SampleData.FUNCTION);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected HomeFunction getDataFromStringArray(String[] datas) {
		return HomeFunction.createFromStringArray(datas);
	}

}
