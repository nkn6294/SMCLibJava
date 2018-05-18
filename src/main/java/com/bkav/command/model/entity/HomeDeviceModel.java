package com.bkav.command.model.entity;

import java.util.Arrays;

import com.bkav.command.model.CommonModel;
import com.bkav.command.test.SampleData;
import com.bkav.home.data.HomeDevice;
import com.bkav.util.CollectionUtil;

public class HomeDeviceModel extends CommonModel<HomeDevice> {
	
	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "DEVICE";
		DATA_PROCESSED = CollectionUtil.convert(SampleData.DEVICES);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected HomeDevice getDataFromStringArray(String[] datas) {
		return HomeDevice.createFromStringArray(datas);
	}
	
}
