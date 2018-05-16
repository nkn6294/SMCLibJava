package com.bkav.command.model.time;

import java.util.Arrays;

import com.bkav.command.data.TimeInDay;
import com.bkav.command.model.CommonModel;
import com.bkav.command.test.ModelData;
import com.bkav.util.CollectionUtil;

public class TimeInDayModel extends CommonModel<TimeInDay> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "TIME_IN_DAY";
		DATA_PROCESSED = CollectionUtil.convert(ModelData.TIME_IN_DAY);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected TimeInDay createDataFromStringArray(String[] datas) {
		return TimeInDay.createFromStringArray(datas);
	}

}