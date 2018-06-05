package com.bkav.command.model.time;

import com.bkav.command.data.TimeUnit;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class TimeUnitModel extends StaticInputWordsModel<TimeUnit> {

	public TimeUnitModel() {
		super(ModelData.TIME_UNITS);
		this.modelName = "TIME_UNIT";
	}

	@Override
	protected TimeUnit getDataFromStringArray(String[] datas) {
		return TimeUnit.createFromStringArray(datas);
	}
}
