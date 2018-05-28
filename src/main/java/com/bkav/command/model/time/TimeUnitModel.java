package com.bkav.command.model.time;

import com.bkav.command.data.TimeUnit;
import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.ModelData;

public class TimeUnitModel extends StaticInputWordsModel<TimeUnit> {

	public TimeUnitModel() {
		super(ModelData.TIME_UNITS);
		this.MODEL_NAME = "TIME_UNIT";
	}

	@Override
	protected TimeUnit getDataFromStringArray(String[] datas) {
		return TimeUnit.createFromStringArray(datas);
	}
}
