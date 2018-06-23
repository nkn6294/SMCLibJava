package com.bkav.command.model.time.repeat;

import com.bkav.command.data.TimeRepeat;
import com.bkav.command.data.TimeRepeatType;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class YearlyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

	public YearlyTimeRepeatModel() {
		super(ModelData.YEARLY_TIME_REPEAT);
		this.modelName = "YEARLY_TIME_REPEAT";
	}

	@Override
	protected TimeRepeat getDataFromStringArray(String[] datas) {
		return TimeRepeat.createFromStringArray(datas, TimeRepeatType.YEARLY);
	}
}