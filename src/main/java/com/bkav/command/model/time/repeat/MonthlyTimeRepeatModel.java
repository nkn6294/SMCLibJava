package com.bkav.command.model.time.repeat;

import com.bkav.command.data.time.TimeRepeat;
import com.bkav.command.data.time.TimeRepeat.TimeRepeatType;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class MonthlyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

	public MonthlyTimeRepeatModel() {
		super(ModelData.MONTHLY_TIME_REPEAT);
		this.modelName = "MONTHLY_TIME_REPEAT";
	}

	@Override
	protected TimeRepeat getDataFromStringArray(String[] datas) {
		return TimeRepeat.createFromStringArray(datas, TimeRepeatType.MONTHLY);
	}
}