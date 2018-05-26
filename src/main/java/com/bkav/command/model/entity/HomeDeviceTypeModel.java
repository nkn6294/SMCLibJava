package com.bkav.command.model.entity;

import java.util.Arrays;

import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.ModelData;
import com.bkav.home.data.HomeDeviceType;
import com.bkav.util.CollectionUtil;

public class HomeDeviceTypeModel extends StaticInputWordsModel<HomeDeviceType> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "DEVICE_TYPE";
		DATA_PROCESSED = CollectionUtil.convert(ModelData.DEVICE_TYPE);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected HomeDeviceType getDataFromStringArray(String[] datas) {
		return HomeDeviceType.createFromStringArray(datas);
	}

}
