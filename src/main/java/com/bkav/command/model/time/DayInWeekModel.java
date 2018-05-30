package com.bkav.command.model.time;

import com.bkav.command.data.DayInWeek;
import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.ModelData;

public class DayInWeekModel extends StaticInputWordsModel<DayInWeek> {

	public DayInWeekModel() {
		super(ModelData.DAY_IN_WEEK);
		this.modelName = "DAY_IN_WEEK";
	}

	@Override
	protected DayInWeek getDataFromStringArray(String[] datas) {
		return DayInWeek.createFromStringArray(datas);
	}
}
