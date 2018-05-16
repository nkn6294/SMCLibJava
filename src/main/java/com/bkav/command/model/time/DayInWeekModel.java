package com.bkav.command.model.time;

import java.util.Arrays;

import com.bkav.command.data.DayInWeek;
import com.bkav.command.model.CommonModel;
import com.bkav.command.test.ModelData;
import com.bkav.util.CollectionUtil;

public class DayInWeekModel extends CommonModel<DayInWeek> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "DAY_IN_WEEK";
		DATA_PROCESSED = CollectionUtil.convert(ModelData.DAY_IN_WEEK);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected DayInWeek createDataFromStringArray(String[] datas) {
		return DayInWeek.createFromStringArray(datas);
	}
}
