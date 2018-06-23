package com.bkav.command.model.time.repeat;

import com.bkav.command.data.TimeRepeat;
import com.bkav.command.data.TimeRepeatType;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class DailyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

	public DailyTimeRepeatModel() {
		super(ModelData.DAILY_TIME_REPEAT);
		this.modelName = "ONCE_TIME_REPEAT";
	}

	@Override
	protected TimeRepeat getDataFromStringArray(String[] datas) {
		return TimeRepeat.createFromStringArray(datas, TimeRepeatType.DAILY);
	}
}