package com.bkav.command.model.time.repeat;

import com.bkav.command.model.CollectionModel;

public class TimeRepeatModel extends CollectionModel { 

	@Override
	protected void init() {
		super.init();
		this.modelName = "TIME_REPEAT";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new OnceTimeRepeatModel(),
				new DailyTimeRepeatModel(),
				new WeeklyTimeRepeatModel(),
				new MonthlyTimeRepeatModel(),
				new YearlyTimeRepeatModel());
	}

}
