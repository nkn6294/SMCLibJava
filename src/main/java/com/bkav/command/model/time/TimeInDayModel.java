package com.bkav.command.model.time;

import com.bkav.command.data.time.TimeInDay;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class TimeInDayModel extends StaticInputWordsModel<TimeInDay> {

	public TimeInDayModel() {
		super(ModelData.TIME_IN_DAY);
		this.modelName = "TIME_IN_DAY";
	}
	
	@Override
	protected TimeInDay getDataFromStringArray(String[] datas) {
		return TimeInDay.createFromStringArray(datas);
	}

}
