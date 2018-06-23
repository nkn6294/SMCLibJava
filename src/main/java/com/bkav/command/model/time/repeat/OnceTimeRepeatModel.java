package com.bkav.command.model.time.repeat;

import com.bkav.command.data.TimeRepeat;
import com.bkav.command.data.TimeRepeatType;
import com.bkav.command.model.ModelData;
import com.bkav.command.model.StaticInputWordsModel;

public class OnceTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

	public OnceTimeRepeatModel() {
		super(ModelData.ONCE_TIME_REPEAT);
		this.modelName = "ONCE_TIME_REPEAT";
	}

	@Override
	protected TimeRepeat getDataFromStringArray(String[] datas) {
		return TimeRepeat.createFromStringArray(datas, TimeRepeatType.ONCE);
	}
}