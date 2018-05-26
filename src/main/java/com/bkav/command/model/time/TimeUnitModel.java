package com.bkav.command.model.time;

import java.util.Arrays;

import com.bkav.command.data.TimeUnit;
import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.ModelData;
import com.bkav.util.CollectionUtil;

public class TimeUnitModel extends StaticInputWordsModel<TimeUnit> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "TIME_UNIT";
		DATA_PROCESSED = CollectionUtil.convert(ModelData.TIME_UNITS);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected TimeUnit getDataFromStringArray(String[] datas) {
		return TimeUnit.createFromStringArray(datas);
	}
}
