package com.bkav.command.model.time;

import java.util.Arrays;

import com.bkav.command.data.TimeRepeat;
import com.bkav.command.model.CommonModel;
import com.bkav.command.test.ModelData;
import com.bkav.util.CollectionUtil;

public class TimeRepeatModel extends CommonModel<TimeRepeat> {

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "TIME_REPEAT";
		DATA_PROCESSED = CollectionUtil.convert(ModelData.REPEATES);
		Arrays.sort(DATA_PROCESSED, DEFAULT_STRING_ARRAY_COMPARATOR);
	}

	@Override
	protected TimeRepeat createDataFromStringArray(String[] datas) {
		return TimeRepeat.createFromStringArray(datas);
	}
}
