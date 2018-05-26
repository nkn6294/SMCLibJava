package com.bkav.command.model.time;

import java.util.Arrays;

import com.bkav.command.data.TimeInDay;
import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.ModelData;
import com.bkav.util.CollectionUtil;

public class TimeInDayModel extends StaticInputWordsModel<TimeInDay> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "TIME_IN_DAY";
		DATA_PROCESSED = CollectionUtil.convert(ModelData.TIME_IN_DAY);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected TimeInDay getDataFromStringArray(String[] datas) {
		return TimeInDay.createFromStringArray(datas);
	}

}
