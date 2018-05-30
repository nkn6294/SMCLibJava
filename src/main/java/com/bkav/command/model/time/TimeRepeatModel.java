package com.bkav.command.model.time;

import com.bkav.command.data.TimeRepeat;
import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.ModelData;

public class TimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {
	
	public TimeRepeatModel() {
		super(ModelData.REPEATES);
		this.modelName = "TIME_REPEAT";
	}

	@Override
	protected TimeRepeat getDataFromStringArray(String[] datas) {
		return TimeRepeat.createFromStringArray(datas);
	}
}
