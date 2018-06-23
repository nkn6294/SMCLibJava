package com.bkav.command.model.time.repeat;

import com.bkav.command.data.TimeRepeat;
import com.bkav.command.data.TimeRepeatType;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class WeeklyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

	public WeeklyTimeRepeatModel() {
		super(ModelData.WEEKLY_TIME_REPEAT);
		this.modelName = "WEEKLY_TIME_REPEAT";
	}

	@Override
	protected TimeRepeat getDataFromStringArray(String[] datas) {
		return TimeRepeat.createFromStringArray(datas, TimeRepeatType.WEEKLY);
	}
}