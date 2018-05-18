package com.bkav.command.model.entity;

import java.util.Arrays;

import com.bkav.command.model.CommonModel;
import com.bkav.command.test.SampleData;
import com.bkav.home.data.HomeArea;
import com.bkav.util.CollectionUtil;

public class HomeAreaModel extends CommonModel<HomeArea> {

	@Override
	protected HomeArea getDataFromStringArray(String[] datas) {
		return HomeArea.createFromStringArray(datas);
	}

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "AREA";
		DATA_PROCESSED = CollectionUtil.convert(SampleData.AREAS);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}
}
